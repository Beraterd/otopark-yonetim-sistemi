package com.berat.ticket.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtUtil {

    private final String secretKeyString = System.getenv("JWT_SECRET"); // En az 256 bit olmalı
    private final long expiration = 1000 * 60 * 60 * 10; // 10 saat

    private final Key secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());

    // JWT token üretme
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT token'dan kullanıcı adını alma
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Token geçerli mi kontrolü
    public boolean validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
