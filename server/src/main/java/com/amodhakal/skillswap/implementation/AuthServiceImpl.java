package com.amodhakal.skillswap.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amodhakal.skillswap.entities.AuthEntity;
import com.amodhakal.skillswap.repository.AuthRepository;
import com.amodhakal.skillswap.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthRepository authRepository;

    @Override
    public String handleSignup(String name, String email, String password) throws IllegalArgumentException {
        if (name == null || email == null || password == null) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        AuthEntity foundUser = authRepository.findByEmail(email);
        if (foundUser != null) {
            throw new IllegalArgumentException("User already exists");
        }

        AuthEntity newUser = AuthEntity.newWithHashedPassword(name, email, password);
        authRepository.save(newUser);

        // TODO: Token here
        return "Signup token";
    }

    @Override
    public String handleSignin(String email, String password) throws IllegalArgumentException {
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

        // TODO: Token here
        return "Signin token";
    }

}
