package com.example.HgsSystem.exception;

public class BadRequestException extends RuntimeException {
    // BadRequestException constructor
    public BadRequestException(String message) {
        super(message);  // Base class constructor'ına mesajı geçiriyoruz
    }
}
