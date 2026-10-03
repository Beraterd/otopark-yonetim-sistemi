package com.hgs.gecis.hgs_gecis.service;


import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hgs.gecis.hgs_gecis.model.AracSahibi;
import com.hgs.gecis.hgs_gecis.model.User;
import com.hgs.gecis.hgs_gecis.repository.AracSahibiRepository;
import com.hgs.gecis.hgs_gecis.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private AracSahibiRepository aracSahibiRepository;

    public double getBalance(Long userId) {
        AracSahibi aracSahibi = aracSahibiRepository.findByUserId(userId);
        return aracSahibi != null ? aracSahibi.getBakiye() : 0;
    }

    public void addBalance(Long userId, double amount) {
        AracSahibi aracSahibi = aracSahibiRepository.findByUserId(userId);
        if (aracSahibi != null) {
            aracSahibi.setBakiye(aracSahibi.getBakiye() + amount);
            aracSahibiRepository.save(aracSahibi);
        }
    }
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(String username, String password, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));  // Şifreyi hashleyerek kaydediyoruz
        user.setRole(role);
        return userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
    
}
