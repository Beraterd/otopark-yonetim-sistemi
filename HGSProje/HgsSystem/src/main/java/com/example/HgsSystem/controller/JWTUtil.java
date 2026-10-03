package com.example.HgsSystem.controller;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import java.security.Key;
import java.util.Date;

public class JWTUtil {

    @Value("${jwt.secret}")  // application.properties dosyasından alıyoruz
    private String SECRET_KEY;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 gün
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build();
        return parser.parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build();
            parser.parseClaimsJws(token);
            return true;
        } catch (SecurityException | io.jsonwebtoken.MalformedJwtException | io.jsonwebtoken.ExpiredJwtException e) {
            return false;
        }
    }
}
