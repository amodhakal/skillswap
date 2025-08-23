package com.amodhakal.skillswap.service;

import com.amodhakal.skillswap.dto.TokenDto;

public interface AuthService {
    public TokenDto handleSignup(String name, String email, String password) throws IllegalArgumentException;

    public TokenDto handleSignin(String email, String password) throws IllegalArgumentException;
}
