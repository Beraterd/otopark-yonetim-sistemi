 package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole());
        return "Kayıt başarılı!";
    }
    

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody UserDTO userDTO) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());

        authManager.authenticate(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());
        String jwt = jwtUtil.generateToken(userDetails.getUsername(), 
                                           userDetails.getAuthorities().stream().findFirst().get().getAuthority().replace("ROLE_", ""));
        return new AuthResponseDTO(jwt);
    }
}
