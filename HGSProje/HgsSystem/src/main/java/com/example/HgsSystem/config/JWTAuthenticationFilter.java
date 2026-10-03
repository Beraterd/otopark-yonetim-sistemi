package com.example.HgsSystem.config;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.HgsSystem.controller.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

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

            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.getUsernameFromToken(token);

                // Kullanıcı doğrulaması yapılır ve güvenlik bağlamına eklenir
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, 
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // İsteği geçirme
        filterChain.doFilter(request, response);
    }
}
