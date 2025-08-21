package com.amodhakal.skillswap.implementation;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amodhakal.skillswap.services.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public String generateToken(UUID userId) {
        long nextWeek = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000;
        byte[] keyBytes = secretKey.getBytes();
        return Jwts.builder()
                .subject(userId.toString())
                .issuedAt(new Date())
                .expiration(new Date(nextWeek))
                .signWith(Keys.hmacShaKeyFor(keyBytes))
                .compact();
    }

}
