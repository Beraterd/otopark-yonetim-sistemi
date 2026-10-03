package com.example.otoparkprojesi.model;

public class DebtDetail {
    private String entryTime;
    private String exitTime; // Yeni alan eklendi
    private double debtAmount;
    private String plateNo;  // Plaka no alanı

    // Getters & Setters
    public String getEntryTime() { return entryTime; }
    public void setEntryTime(String entryTime) { this.entryTime = entryTime; }

    public String getExitTime() { return exitTime; }
    public void setExitTime(String exitTime) { this.exitTime = exitTime; }

    public double getDebtAmount() { return debtAmount; }
    public void setDebtAmount(double debtAmount) { this.debtAmount = debtAmount; }

    public String getPlateNo() { return plateNo; }
    public void setPlateNo(String plateNo) { this.plateNo = plateNo; }
}
