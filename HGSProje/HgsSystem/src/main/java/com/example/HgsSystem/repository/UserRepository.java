package com.example.HgsSystem.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.HgsSystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Kullanıcı adı ile kullanıcıyı bulmaya yarayan method
    Optional<User> findByUsername(String username);
}
