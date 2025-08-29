package com.amodhakal.skillswap.implementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amodhakal.skillswap.dto.TokenDto;
import com.amodhakal.skillswap.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public TokenDto generateToken(UUID userId) {
        Long expirationDate = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000; // Next week
        byte[] keyBytes = secretKey.getBytes();

        String token = Jwts.builder()
                .subject(userId.toString())
                .issuedAt(new Date())
                .expiration(new Date(expirationDate))
                .signWith(Keys.hmacShaKeyFor(keyBytes))
                .compact();

        return new TokenDto(token, expirationDate);
    }

    @Override
    public UUID getUserIdFromToken(String token) throws IllegalArgumentException {
        try {
            byte[] keyBytes = secretKey.getBytes();
            Claims claims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String userIdStr = claims.getSubject();
            if (userIdStr == null) {
                throw new IllegalArgumentException();
            }
            return UUID.fromString(userIdStr);
        } catch (io.jsonwebtoken.ExpiredJwtException exception) {
            throw new IllegalArgumentException("Expiration token");
        } catch (JwtException exception) {
            throw new IllegalArgumentException("Invalid token");
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Invalid token");
        }
    }
}
