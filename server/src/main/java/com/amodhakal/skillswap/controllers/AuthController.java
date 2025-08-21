package com.amodhakal.skillswap.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.amodhakal.skillswap.dto.AuthDto;
import com.amodhakal.skillswap.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Object> handleSignup(@RequestBody AuthDto authDto) {
        try {
            String token = authService.handleSignup(authDto.getName(), authDto.getEmail(), authDto.getPassword());
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/signin")
    public Object handleSignin(@RequestBody AuthDto authDto) {
        try {
            String token = authService.handleSignin(authDto.getEmail(), authDto.getPassword());
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
