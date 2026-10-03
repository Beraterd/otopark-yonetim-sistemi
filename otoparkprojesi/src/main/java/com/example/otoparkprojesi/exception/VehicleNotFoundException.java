package com.example.otoparkprojesi.exception;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String plateNo) {
        super("Araç bulunamadı. Plaka: " + plateNo);
    }
}
