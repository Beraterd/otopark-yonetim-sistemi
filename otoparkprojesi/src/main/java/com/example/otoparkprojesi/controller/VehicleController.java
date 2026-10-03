package com.example.otoparkprojesi.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.otoparkprojesi.model.DebtInfo;
import com.example.otoparkprojesi.model.PaymentResult;
import com.example.otoparkprojesi.model.Vehicle;
import com.example.otoparkprojesi.service.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/entry")
    public Vehicle addEntry(
            @RequestParam String plateNo,
            @RequestParam(required = false) String entryTime
    ) {
        LocalDateTime parsedTime;
        try {
            parsedTime = (entryTime == null || entryTime.isBlank())
                    ? LocalDateTime.now()
                    : LocalDateTime.parse(entryTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception e) {
            throw new RuntimeException("Giriş zamanı formatı hatalı: " + entryTime, e);
        }

        try {
            return vehicleService.addEntry(plateNo, parsedTime);
        } catch (RuntimeException e) {
            throw new RuntimeException("Bu araç çıkış yapmadan tekrar giriş yapamaz. Önce çıkış yapılmalı!", e);
        }
    }

    @PutMapping("/exit")
    public Vehicle makeExit(
            @RequestParam String plateNo,
            @RequestParam(required = false) String exitTime
    ) {
        LocalDateTime parsedExitTime;
        try {
            if (exitTime == null || exitTime.isBlank()) {
                parsedExitTime = LocalDateTime.now();
            } else {
                String cleaned = exitTime.trim();
                parsedExitTime = cleaned.contains("T")
                        ? LocalDateTime.parse(cleaned, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        : LocalDateTime.parse(cleaned, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Çıkış zamanı formatı hatalı: " + exitTime, e);
        }

        return vehicleService.makeExit(plateNo, parsedExitTime);
    }

    @GetMapping("/debt")
    public DebtInfo getDebt(@RequestParam String plateNo) {
        return vehicleService.getDebt(plateNo);
    }

    @PostMapping("/pay")
    public PaymentResult makePayment(@RequestParam String plateNo) {
        return vehicleService.makePayment(plateNo);
    }

    @PostMapping("/pay/{debtId}")
    public PaymentResult makePaymentByDebtId(@RequestParam String plateNo, @PathVariable int debtId) {
        return vehicleService.makePayment(plateNo, debtId);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/entry-vehicles")
    public List<Vehicle> getEntryVehicles() {
        return vehicleService.getEntryVehicles();
    }

    @GetMapping("/exit-vehicles")
    public List<Vehicle> getExitVehicles() {
        return vehicleService.getExitVehicles();
    }
}
