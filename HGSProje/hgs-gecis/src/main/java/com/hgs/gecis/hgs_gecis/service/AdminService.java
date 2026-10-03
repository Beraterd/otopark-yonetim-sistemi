package com.hgs.gecis.hgs_gecis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgs.gecis.hgs_gecis.model.AracSahibi;
import com.hgs.gecis.hgs_gecis.model.Gise;

@Service
public class AdminService {

    @Autowired
    private GiseRepository giseRepository;
    
    @Autowired
    private GecisRepository gecisRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public void addGise(String giseAdi, String giseKodu, double gecisUcreti) {
        Gise gise = new Gise();
        gise.setGiseAdi(giseAdi);
        gise.setGiseKodu(giseKodu);
        gise.setGecisUcreti(gecisUcreti);
        giseRepository.save(gise);
    }
    
    public void updateGisePrice(Long giseId, double newPrice) {
        Gise gise = giseRepository.findById(giseId).orElseThrow(() -> new RuntimeException("Gise not found"));
        gise.setGecisUcreti(newPrice);
        giseRepository.save(gise);
    }
    
    public void updateUserBalance(Long userId, double amount) {
        AracSahibi aracSahibi = aracSahibiRepository.findByUserId(userId);
        if (aracSahibi != null) {
            aracSahibi.setBakiye(amount);
            aracSahibiRepository.save(aracSahibi);
        }
    }
}
