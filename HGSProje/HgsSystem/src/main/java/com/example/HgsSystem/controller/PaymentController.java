package com.example.HgsSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.HgsSystem.entity.Payment;
import com.example.HgsSystem.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestParam Long userId, @RequestParam double amount, @RequestParam String description) {
        Payment payment = paymentService.createPayment(userId, amount, description);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }
}
