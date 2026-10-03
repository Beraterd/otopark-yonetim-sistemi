package com.hgs.gecis.hgs_gecis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hgs.gecis.hgs_gecis.model.Gecis;

@Repository
public interface GecisRepository extends JpaRepository<Gecis, Long> {
    List<Gecis> findByUserId(Long userId);
}
