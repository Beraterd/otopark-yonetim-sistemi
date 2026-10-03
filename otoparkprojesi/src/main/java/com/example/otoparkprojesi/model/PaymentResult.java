package com.example.otoparkprojesi.model;

public class PaymentResult {
    private boolean success;
    private String message;
    private double paidAmount;

    // Getters & Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(double paidAmount) { this.paidAmount = paidAmount; }
}
