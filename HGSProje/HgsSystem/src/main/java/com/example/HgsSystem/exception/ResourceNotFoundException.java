package com.example.HgsSystem.exception;

public class ResourceNotFoundException extends RuntimeException {
    // ResourceNotFoundException constructor
    public ResourceNotFoundException(String message) {
        super(message);  // Base class constructor'ına mesajı geçiriyoruz
    }
}
