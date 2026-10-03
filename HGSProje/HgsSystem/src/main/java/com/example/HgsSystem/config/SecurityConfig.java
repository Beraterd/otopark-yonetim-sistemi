package com.example.HgsSystem.config;

import com.example.HgsSystem.security.JWTAuthenticationFilter;
import com.example.HgsSystem.security.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JWTUtil jwtUtil;

    public SecurityConfig(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // PasswordEncoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Güvenlik yapılandırmasını yapıyoruz
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // CSRF korumasını devre dışı bırakıyoruz çünkü JWT kullanıyoruz

            // Swagger UI ve API dokümantasyonuna herkese izin veriyoruz
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/api/auth/**").permitAll()  // Kimlik doğrulama işlemleri
                .requestMatchers("/api/users").permitAll()  // Kullanıcı kaydına herkes erişebilir
                .requestMatchers("/api/payments/**").authenticated() // Ödemeler için doğrulama
                .anyRequest().authenticated() // Diğer tüm API'ler için doğrulama
            )
            
            // JWT Authentication Filter'ı burada ekliyoruz
            .addFilterBefore(new JWTAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
