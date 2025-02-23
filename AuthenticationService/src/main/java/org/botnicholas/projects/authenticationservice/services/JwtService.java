package org.botnicholas.projects.authenticationservice.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.botnicholas.projects.authenticationservice.exceptions.SecretNotReadException;
import org.botnicholas.projects.authenticationservice.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey accessSecret;
    private final SecretKey refreshSecret;

    public JwtService(
            @Value("${access-secret}") String accessSecret,
            @Value("${refresh-secret}") String refreshSecret) throws SecretNotReadException {
        if (accessSecret == null || refreshSecret == null) {
            throw new SecretNotReadException("Secrets weren't read properly!");
        }
        this.accessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret));
        this.refreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecret));
    }

    public String generateAccessToken(User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant expirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant();
        final Date expiryDate = Date.from(expirationInstant);

        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(expiryDate)
                .signWith(accessSecret)
                .claim("role", user.getRole())
                .compact();
    }

    public String generateRefreshToken(User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant expirationInstant = now.plusHours(1).atZone(ZoneId.systemDefault()).toInstant();
        final Date expiryDate = Date.from(expirationInstant);

        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(expiryDate)
                .signWith(refreshSecret)
                .compact();
    }

    public boolean validateAccessToken(String token) {
        return validateToken(token, accessSecret);
    }

    public boolean validateRefreshToken(String token) {
        return validateToken(token, refreshSecret);
    }

    private boolean validateToken(String token, SecretKey key) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getAccessClaims(String token) {
        return getClaims(token, accessSecret);
    }

    public Claims getRefreshClaims(String token) {
        return getClaims(token, refreshSecret);
    }

    private Claims getClaims(String token, SecretKey key) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
