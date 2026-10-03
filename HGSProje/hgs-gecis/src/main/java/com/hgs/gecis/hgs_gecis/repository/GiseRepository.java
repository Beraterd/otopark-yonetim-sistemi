package com.hgs.gecis.hgs_gecis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hgs.gecis.hgs_gecis.model.Gise;

@Repository
public interface GiseRepository extends JpaRepository<Gise, Long> {
}
