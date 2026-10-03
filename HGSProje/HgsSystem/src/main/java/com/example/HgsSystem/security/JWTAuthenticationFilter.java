package com.example.HgsSystem.security;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    // JWTUtil'i constructor üzerinden alıyoruz
    public JWTAuthenticationFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Authorization header'ını al
        String header = request.getHeader("Authorization");

        // Eğer header null değilse ve "Bearer " ile başlıyorsa
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);  // "Bearer " kısmını kesip token'ı al

            // Token geçerli ise kullanıcıyı doğrula
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.getUsernameFromToken(token);

                // SecurityContext'e authentication bilgilerini ekle
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(username, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // İsteği bir sonraki filtreye ilet
        filterChain.doFilter(request, response);
    }
}
