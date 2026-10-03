package com.example.Hgs.repository;

import com.example.Hgs.model.Gise;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GiseRepository extends JpaRepository<Gise, Long> {
    Optional<Gise> findByGiseKodu(String giseKodu);
    Optional<Gise> findByAd(String ad);
}
