package com.example.demo.demo.controller;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.demo.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.demo.com.example.config.JwtService;
import com.example.demo.demo.model.Role;
import com.example.demo.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public AuthenticationResponse register(RegisterRequest request) {
        
        var user=User.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();
        repository.save(user);
        var jwtToken=jwtService.generateToken(user);


        return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();

    }
    public AuthenticationResponse unregister(String token) {
        // Extract username from JWT token
        String username = jwtService.extractUsername(token.substring(7)); // Remove "Bearer "
    
        // Find the user in the database
        var user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    
        // Delete the user from the database
        repository.delete(user);
    
        return AuthenticationResponse.builder()
                .token("User successfully unregistered.") // You can return a message instead of a token
                .build();
    }
    




    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user= repository.findByUsername(request.getUsername())
        .orElseThrow();

        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
    }
    
}
