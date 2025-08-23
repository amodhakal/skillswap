package com.amodhakal.skillswap.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amodhakal.skillswap.dto.TokenDto;
import com.amodhakal.skillswap.entity.AuthEntity;
import com.amodhakal.skillswap.repository.AuthRepository;
import com.amodhakal.skillswap.service.AuthService;
import com.amodhakal.skillswap.service.TokenService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    public TokenDto handleSignup(String name, String email, String password) throws IllegalArgumentException {
        if (name == null || email == null || password == null) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        AuthEntity foundUser = authRepository.findByEmail(email);
        if (foundUser != null) {
            throw new IllegalArgumentException("User already exists");
        }

        AuthEntity newUser = AuthEntity.newWithHashedPassword(name, email, password);
        authRepository.save(newUser);

        return tokenService.generateToken(newUser.getId());
    }

    @Override
    public TokenDto handleSignin(String email, String password) throws IllegalArgumentException {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        AuthEntity foundUser = authRepository.findByEmail(email);
        if (foundUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (!foundUser.isValidHashedPassword(password)) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return tokenService.generateToken(foundUser.getId());
    }

}
