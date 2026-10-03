package com.example.otoparkprojesi.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
	
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")  // Veritabanındaki kolon adı
    private int id;

    @Column(name = "plate_no", nullable = false)
    private String plateNo;

    @Column(name = "EntryTime")
    private LocalDateTime entryTime;

    @Column(name = "exit_time")   // Veritabanındaki kolon adı
    private LocalDateTime exitTime;

    @Column(name = "total_fee")   // Veritabanındaki kolon adı
    private double totalFee;

    @Column(name = "is_paid")
    @JsonProperty("paid")  // frontend için alias
    private boolean isPaid;

    @Column(name = "status")
    private String status;
    
    // Yeni eklenen alanlar
    private String ownerName = "Demo Kullanıcı";
    private String ownerEmail = "demo@example.com";


    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getOwnerEmail() {
        return ownerEmail;
    }
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPlateNo() { return plateNo; }
    public void setPlateNo(String plateNo) { this.plateNo = plateNo; }

    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }

    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }

    public double getTotalFee() { return totalFee; }
    public void setTotalFee(double totalFee) { this.totalFee = totalFee; }

    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean paid) { isPaid = paid; }
    

}
	