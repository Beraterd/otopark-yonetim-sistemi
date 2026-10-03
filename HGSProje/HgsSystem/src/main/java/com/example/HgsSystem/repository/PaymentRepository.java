package com.example.HgsSystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.HgsSystem.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Kullanıcı ID'sine göre ödeme geçmişini sorgulayan method
    List<Payment> findByUserId(Long userId);
}
