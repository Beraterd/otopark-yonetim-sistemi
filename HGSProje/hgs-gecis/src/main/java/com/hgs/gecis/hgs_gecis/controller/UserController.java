package com.hgs.gecis.hgs_gecis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hgs.gecis.hgs_gecis.model.User;
import com.hgs.gecis.hgs_gecis.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/balance")
    public double getBalance(@AuthenticationPrincipal User user) {
        return userService.getBalance(user.getId());
    }

    @PostMapping("/add-balance")
    public void addBalance(@AuthenticationPrincipal User user, @RequestParam double amount) {
        userService.addBalance(user.getId(), amount);
    }
}

