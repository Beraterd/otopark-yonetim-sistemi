	package com.example.demo.service;
	
	import com.example.demo.entity.User;
	import com.example.demo.repository.UserRepository;
	
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.stereotype.Service;
	
	@Service
	public class UserService {
	
	    private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder; // Spring Security kullanacağız
	
	    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }
	
	    public User registerUser(String username, String password, String role) {
	        User user = new User();
	        user.setUsername(username);
	        user.setPassword(passwordEncoder.encode(password));
	        user.setRole(role);
	        if (userRepository.findByUsername(username).isPresent()) {
	            throw new RuntimeException("Bu kullanıcı adı zaten alınmış.");
	        }
	
	        return userRepository.save(user);
	        
	    }
	
	    public User findByUsername(String username) {
	        return userRepository.findByUsername(username).orElse(null);
	    }
	}
