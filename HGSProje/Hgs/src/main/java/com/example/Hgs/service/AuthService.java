package com.example.Hgs.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Hgs.config.JwtUtil;
import com.example.Hgs.dto.JwtResponse;
import com.example.Hgs.dto.LoginRequest;
import com.example.Hgs.dto.RegisterRequest;
import com.example.Hgs.exception.CustomExceptions;
import com.example.Hgs.exception.CustomExceptions.UserAlreadyExistsException;
import com.example.Hgs.exception.CustomExceptions.UserNotFoundException;
import com.example.Hgs.model.AppUser;
import com.example.Hgs.model.Role;
import com.example.Hgs.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private  AppUserRepository userRepository;
    private  PasswordEncoder passwordEncoder;
    private  JwtUtil jwtUtil;

    // Giriş yap
    public JwtResponse login(LoginRequest request) {
        AppUser user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
        	throw new CustomExceptions.InvalidPasswordException("Şifre hatalı");

        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        return new JwtResponse(token, user.getRole().name());
    }

    // Kayıt ol
    public AppUser register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException("Kullanıcı adı zaten mevcut");
        }

        AppUser user = AppUser.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role() == null ? Role.USER : request.role())
                .build();

        return userRepository.save(user);
    }
}
