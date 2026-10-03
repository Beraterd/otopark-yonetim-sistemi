package com.example.Hgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Hgs.model.AppUser;
import com.example.Hgs.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    

    // Bakiye görüntüle
    @GetMapping("/bakiye")
    public ResponseEntity<Double> bakiyeGoruntule(@AuthenticationPrincipal UserDetails userDetails) {
        AppUser user = new AppUser();
        user.setUsername(userDetails.getUsername());
        return ResponseEntity.ok(userService.bakiyeGoruntule(user));
    }

    // Bakiye ekle
    @PostMapping("/bakiye")
    public ResponseEntity<String> bakiyeEkle(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam double miktar) {

        AppUser user = new AppUser();
        user.setUsername(userDetails.getUsername());
        userService.bakiyeEkle(user, miktar);
        return ResponseEntity.ok("Bakiye eklendi: " + miktar);
    }
    
}

