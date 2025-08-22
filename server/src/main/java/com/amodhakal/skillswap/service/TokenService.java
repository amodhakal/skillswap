package com.amodhakal.skillswap.service;

import java.util.UUID;

public interface TokenService {
    String generateToken(UUID userId);
}
