package com.typingTitans.lobotomy_lab.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.typingTitans.lobotomy_lab.utils.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;


  @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(csrf -> csrf
            .ignoringRequestMatchers("/auth/login") // Disable CSRF for login endpoint
        )
        .authorizeHttpRequests(
            authorize -> authorize.requestMatchers(
                "/auth/login", 
                "/login/refresh**", 
                "/logout**",
                "/v2/api-docs/**", // docs
                "/v3/api-docs/**", // Swagger v3 API docs
                "/swagger-resources/**", // Swagger resources
                "/swagger-ui/**", // Swagger UI
                "/webjars/**", // Webjars for Swagger UI
                "/swagger-ui.html", // Swagger UI HTML
                "/api/v1/firmware/**"
            ).permitAll().anyRequest().authenticated()
        )
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .logout(logout -> logout.disable())
        .httpBasic(Customizer.withDefaults())
        .cors(Customizer.withDefaults())
        .build();
}


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
