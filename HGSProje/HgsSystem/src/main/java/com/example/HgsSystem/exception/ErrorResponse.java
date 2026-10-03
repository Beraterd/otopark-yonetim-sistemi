package com.example.HgsSystem.exception;

public class ErrorResponse {
    private String message;
    
    // Constructor
    public ErrorResponse(String message) {
        this.message = message;
    }

    // Getter ve Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
