package com.typingTitans.lobotomy_lab.utils;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.typingTitans.lobotomy_lab.services.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtUtil jwtUtil;
    private final MyUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, MyUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        logger.info("JwtAuthenticationFilter entered");

        String requestPath = request.getRequestURI();
        logger.info("Processing request to: {}", requestPath);

        // Skip filter for login endpoint
        if ("/auth/login".equals(requestPath)) {
            logger.info("Skipping JWT filter for login endpoint");
            chain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            logger.info("Extracted JWT: {}", jwt);
            username = jwtUtil.extractUsername(jwt);
            logger.info("Extracted username from JWT: {}", username);
        } else {
            logger.info("No JWT found in Authorization header or incorrect format");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            logger.info("Loaded UserDetails for username: {}", username);

            if (jwtUtil.isTokenValid(jwt, userDetails)) {
                logger.info("JWT is valid for user: {}", username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                logger.info("JWT is invalid for user: {}", username);
            }
        } else if (username != null) {
            logger.info("User already authenticated: {}", username);
        }

        chain.doFilter(request, response);
        logger.info("Completed processing for request to: {}", requestPath);
    }
}
