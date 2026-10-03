package com.example.HgsSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.HgsSystem.entity.User;
import com.example.HgsSystem.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user.getUsername(), user.getPassword(), "USER");
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existingUser = userService.findUserByUsername(user.getUsername());
        if (existingUser != null && bCryptPasswordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return jwtUtil.generateToken(existingUser.getUsername());
        }
        throw new RuntimeException("Invalid credentials");
    }
}
