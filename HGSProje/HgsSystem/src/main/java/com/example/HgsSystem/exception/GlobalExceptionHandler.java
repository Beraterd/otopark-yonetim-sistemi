package com.example.HgsSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Genel exception handler: Herhangi bir exception'da bu metod devreye girer.
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        // Genel hata mesajı döner
        return new ResponseEntity<>(new ErrorResponse("Hata oluştu: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ResourceNotFoundException handle
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    // BadRequestException handle
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
