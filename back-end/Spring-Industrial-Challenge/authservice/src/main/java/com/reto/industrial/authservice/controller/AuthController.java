package com.reto.industrial.authservice.controller;

import com.reto.industrial.authservice.client.UserClient;
import com.reto.industrial.authservice.config.JwtUtil;
import com.reto.industrial.authservice.model.AuthRequest;
import com.reto.industrial.authservice.model.AuthResponse;
import com.reto.industrial.authservice.model.UserDto;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            UserDto user = userClient.getByUsername(request.getUsername());

            if (user == null || !user.getPassword().equals(request.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }

            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found or error");
        }
    }

    @GetMapping("/protected")
    public ResponseEntity<String> test(@AuthenticationPrincipal Jwt principal, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        System.out.println("TOKEN RECIBIDO: " + token);

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o ausente");
        }

        return ResponseEntity.ok("Hello " + principal.getSubject() + ", you're authenticated!");
    }

}
