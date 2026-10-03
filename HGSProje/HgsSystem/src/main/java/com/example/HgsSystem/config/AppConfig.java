package com.example.HgsSystem.config;

import com.example.HgsSystem.controller.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }
}
