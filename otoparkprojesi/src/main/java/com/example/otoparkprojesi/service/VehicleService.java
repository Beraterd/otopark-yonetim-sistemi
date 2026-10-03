package com.example.otoparkprojesi.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.otoparkprojesi.model.DebtInfo;
import com.example.otoparkprojesi.model.PaymentResult;
import com.example.otoparkprojesi.model.Vehicle;

public interface VehicleService {
	Vehicle addEntry(String plateNo, LocalDateTime entryTime);
    Vehicle makeExit(String plateNo, LocalDateTime exitTime);
    DebtInfo getDebt(String plateNo);
    PaymentResult makePayment(String plateNo); // Eskisi
    PaymentResult makePayment(String plateNo, int debtId); // Yeni versiyon
    List<Vehicle> getAllVehicles();
    List<Vehicle> getEntryVehicles();
    List<Vehicle> getExitVehicles();
}
