package com.hgs.gecis.hgs_gecis.controller;

import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hgs.gecis.hgs_gecis.model.User;
import com.hgs.gecis.hgs_gecis.service.AdminService;
import com.hgs.gecis.hgs_gecis.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add-gise")
    public void addGise(@RequestParam String giseAdi, @RequestParam String giseKodu, @RequestParam double gecisUcreti) {
        adminService.addGise(giseAdi, giseKodu, gecisUcreti);
    }

    @PostMapping("/update-gise-price")
    public void updateGisePrice(@RequestParam Long giseId, @RequestParam double newPrice) {
        adminService.updateGisePrice(giseId, newPrice);
    }

    @PostMapping("/update-user-balance")
    public void updateUserBalance(@RequestParam Long userId, @RequestParam double amount) {
        adminService.updateUserBalance(userId, amount);
    }
    
    @Autowired
    private UserService userService;

    @PostMapping("/add-user")
    public User addUser(@RequestParam String username, @RequestParam String password, @RequestParam Role role) {
        return userService.createUser(username, password, role);
    }

    @GetMapping("/get-users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}

