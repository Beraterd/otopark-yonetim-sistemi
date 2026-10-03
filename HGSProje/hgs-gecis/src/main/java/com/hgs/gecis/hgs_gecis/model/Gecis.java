package com.hgs.gecis.hgs_gecis.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Gecis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plaka;
    private String ilkGise;
    private String sonGise;
    private double gecisUcreti;
    private LocalDateTime gecisTarihi;
    private boolean gecisDurumu;

    @ManyToOne
    private User user;

    // Getters and Setters
}
