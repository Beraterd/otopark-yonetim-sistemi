package com.example.Hgs.service;

import org.springframework.stereotype.Service;

import com.example.Hgs.exception.CustomExceptions;
import com.example.Hgs.exception.CustomExceptions.AracSahibiNotFoundException;
import com.example.Hgs.model.AppUser;
import com.example.Hgs.model.AracSahibi;
import com.example.Hgs.repository.AracSahibiRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private  AracSahibiRepository aracSahibiRepository;

    // Bakiye görüntüle
    public double bakiyeGoruntule(AppUser user) {
        return aracSahibiRepository.findByUser(user)
                .map(AracSahibi::getBakiye)
                .orElseThrow(() -> new CustomExceptions.AracSahibiNotFoundException("Araç sahibi bulunamadı"));
    }

    // Bakiye ekle
    public void bakiyeEkle(AppUser user, double miktar) {
        if (miktar <= 0) {
            throw new  CustomExceptions.InvalidAmountException("Eklenen miktar geçersiz");
        }

        AracSahibi aracSahibi = aracSahibiRepository.findByUser(user)
                .orElseThrow(() -> new CustomExceptions.AracSahibiNotFoundException("Araç sahibi bulunamadı"));

        aracSahibi.setBakiye(aracSahibi.getBakiye() + miktar);
        aracSahibiRepository.save(aracSahibi);
    }
}
