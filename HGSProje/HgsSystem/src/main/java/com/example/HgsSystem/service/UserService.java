package com.example.HgsSystem.service;

import com.example.HgsSystem.entity.User;
import com.example.HgsSystem.exception.UserAlreadyExistsException; // Import ettik
import com.example.HgsSystem.repository.UserRepository;
import com.example.HgsSystem.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // Kullanıcı kaydı yapma
    public User registerUser(String username, String password, String role) {
        // Kullanıcı mevcut mu kontrol et
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with username: " + username);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));  // Şifreyi şifreliyoruz
        user.setRole(role);

        return userRepository.save(user);
    }

    // Kullanıcıyı username'e göre bulma
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }

    // Kullanıcıyı kaydetme
    public User save(User user) {
        return userRepository.save(user);
    }
}
