package com.example.HgsSystem.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.HgsSystem.entity.Payment;
import com.example.HgsSystem.repository.PaymentRepository;
import com.example.HgsSystem.entity.User;
import com.example.HgsSystem.repository.UserRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    // Ödeme kaydı oluşturma ve kullanıcının bakiyesini güncelleme
    public Payment createPayment(Long userId, double amount, String description) {
        // Kullanıcıyı bul
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Kullanıcı bakiyesi güncelleniyor
        user.setBalance(user.getBalance() - amount);
        userRepository.save(user);

        // Yeni ödeme kaydını oluştur
        Payment payment = new Payment();
        payment.setUser(user);  // Kullanıcıyı ilişkilendir
        payment.setAmount(amount);
        payment.setDescription(description);
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }
}
