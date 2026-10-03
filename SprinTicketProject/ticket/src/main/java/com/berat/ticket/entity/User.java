package com.berat.ticket.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.berat.ticket.repository.UserRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users") // SQL'deki doğru tablo adı
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    
    private static final long serialVersionUID = 1L;


    // İstersen roller için ayrı bir alan ve enum ekleyebilirsin

    // ----- GETTER & SETTER -----
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ----- UserDetails implementasyonları -----

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Roller eklersen burada dönebilirsin
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Component
    public class DataInitializer implements CommandLineRunner {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public void run(String... args) {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User user = new User();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("admin123")); // BCrypt hash!
                userRepository.save(user);
                System.out.println(">> Admin user created");
            }
        }
    }

}
