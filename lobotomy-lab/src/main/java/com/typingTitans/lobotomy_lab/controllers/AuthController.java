package com.typingTitans.lobotomy_lab.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.typingTitans.lobotomy_lab.utils.AuthRequest;
import com.typingTitans.lobotomy_lab.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        logger.info("Attempting to authenticate user: {}", authRequest.getUsername());

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            logger.info("User authenticated successfully: {}", authentication.getName());
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}", authRequest.getUsername(), e);
            throw e;
        }

        String token = jwtUtil.generateToken(authentication.getName());
        logger.info("Generated JWT token for user: {}", authentication.getName());
        
        return token;
    }
}
