package com.example.Hgs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Hgs.model.AppUser;
import com.example.Hgs.model.AracSahibi;

public interface AracSahibiRepository extends JpaRepository<AracSahibi, Long> {
    // Kullanıcıya göre arac sahibini bulma metodu
    Optional<AracSahibi> findByUser(AppUser user);
}