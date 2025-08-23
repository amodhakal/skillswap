package com.amodhakal.skillswap.service;

import java.util.UUID;

import com.amodhakal.skillswap.dto.TokenDto;

public interface TokenService {
    TokenDto generateToken(UUID userId);
}
