package com.example.HgsSystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerUIConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Swagger UI path'i için yönlendirme
        registry.addViewController("/swagger-ui/").setViewName("forward:/swagger-ui/index.html");
    }
}
