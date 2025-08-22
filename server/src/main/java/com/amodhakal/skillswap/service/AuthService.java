package com.amodhakal.skillswap.service;

public interface AuthService {
    public String handleSignup(String name, String email, String password) throws IllegalArgumentException;

    public String handleSignin(String email, String password) throws IllegalArgumentException;
}
