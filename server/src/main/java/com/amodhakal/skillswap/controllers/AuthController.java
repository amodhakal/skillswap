package com.amodhakal.skillswap.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.amodhakal.skillswap.dto.AuthDto;
import com.amodhakal.skillswap.dto.TokenDto;
import com.amodhakal.skillswap.service.AuthService;
import com.amodhakal.skillswap.service.ErrorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private ErrorService errorService;

    @PostMapping("/signup")
    public ResponseEntity<Object> handleSignup(@RequestBody AuthDto authDto) {
        try {
            TokenDto token = authService.handleSignup(authDto.getName(), authDto.getEmail(), authDto.getPassword());
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException exception) {
            return errorService.handleErrorResponse(exception);
        }
    }

    @PostMapping("/signin")
    public Object handleSignin(@RequestBody AuthDto authDto) {
        try {
            TokenDto token = authService.handleSignin(authDto.getEmail(), authDto.getPassword());
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException exception) {
            return errorService.handleErrorResponse(exception);
        }
    }

}
