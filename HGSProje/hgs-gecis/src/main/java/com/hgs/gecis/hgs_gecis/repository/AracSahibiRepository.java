package com.hgs.gecis.hgs_gecis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hgs.gecis.hgs_gecis.model.AracSahibi;

@Repository
public interface AracSahibiRepository extends JpaRepository<AracSahibi, Long> {
    AracSahibi findByUserId(Long userId);
}
