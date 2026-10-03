package com.example.otoparkprojesi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.otoparkprojesi.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    List<Vehicle> findByPlateNo(String plateNo);
    List<Vehicle> findByStatus(String status);  // Giriş yapmış araçları getiren metod
    
}
