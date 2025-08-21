package com.amodhakal.skillswap.services;

import java.util.UUID;

public interface TokenService {
    String generateToken(UUID userId);
}
