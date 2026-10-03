package com.example.HgsSystem.security;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;

@Component
public class JWTUtil {

    // Güvenlik anahtarımız (32 karakterlik)
    private final String SECRET_KEY = System.getenv("JWT_SECRET"); 

    // JWT token'ı imzalayabilmek için signing key elde et
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT token üret
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 gün
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // Token'dan kullanıcı adını al
    public String getUsernameFromToken(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build();
        return parser.parseClaimsJws(token).getBody().getSubject();
    }

    // Token geçerliliğini doğrula
    public boolean validateToken(String token) {
        try {
            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build();
            parser.parseClaimsJws(token);  // Token'ı parse et ve doğrula
            return true;
        } catch (SecurityException | io.jsonwebtoken.MalformedJwtException | io.jsonwebtoken.ExpiredJwtException e) {
            // Hataları loglayabiliriz veya başka bir şekilde handle edebiliriz
            return false;
        }
    }
}
