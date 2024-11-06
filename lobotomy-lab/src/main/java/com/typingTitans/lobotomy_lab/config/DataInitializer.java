package com.typingTitans.lobotomy_lab.config;

import com.typingTitans.lobotomy_lab.entities.User;
import com.typingTitans.lobotomy_lab.enuns.Role;
import com.typingTitans.lobotomy_lab.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initUsers() {
        return args -> {
            if (userRepository.findByUsername("admin_user")==null) {
                User adminUser = new User(
                    null,
                    "admin_user",
                    passwordEncoder.encode("admin123"),
                    Set.of(Role.ADMIN)
                );
                userRepository.save(adminUser);
                System.out.println("Usuário ADMIN criado com sucesso.");
            }

            if (userRepository.findByUsername("regular_user") == null) {
                User regularUser = new User(
                    null,
                    "regular_user",
                    passwordEncoder.encode("user123"),
                    Set.of(Role.USER)
                );
                userRepository.save(regularUser);
                System.out.println("Usuário USER criado com sucesso.");
            }
        };
    }
}
