package com.hgs.gecis.hgs_gecis.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgs.gecis.hgs_gecis.model.Gecis;
import com.hgs.gecis.hgs_gecis.model.Gise;
import com.hgs.gecis.hgs_gecis.model.User;
import com.hgsystem.repository.GecisRepository;
import com.hgsystem.repository.GiseRepository;
import com.hgsystem.repository.UserRepository;

@Service
public class GecisService {

    @Autowired
    private GecisRepository gecisRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GiseRepository giseRepository;

    public String processGecis(Long userId, Long ilkGiseId, Long sonGiseId, String plaka) {
        // Kullanıcıyı al
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "Kullanıcı bulunamadı";
        }

        // Gişe bilgilerini al
        Gise ilkGise = giseRepository.findById(ilkGiseId).orElse(null);
        Gise sonGise = giseRepository.findById(sonGiseId).orElse(null);
        if (ilkGise == null || sonGise == null) {
            return "Gişe bilgisi eksik";
        }

        // Fiyat hesaplama (gidilen mesafeye göre)
        double fiyat = hesaplaFiyat(ilkGise, sonGise);

        // Kullanıcı bakiyesi yeterliyse geçişi işle
        if (user.getAracSahibi().getBakiye() >= fiyat) {
            Gecis gecis = new Gecis();
            gecis.setPlaka(plaka);
            gecis.setIlkGise(ilkGise.getGiseAdi());
            gecis.setSonGise(sonGise.getGiseAdi());
            gecis.setGecisUcreti(fiyat);
            gecis.setGecisTarihi(LocalDateTime.now());
            gecis.setGecisDurumu(true);
            gecis.setUser(user);

            // Geçiş kaydını veritabanına kaydet
            gecisRepository.save(gecis);

            // Kullanıcının bakiyesini güncelle
            user.getAracSahibi().setBakiye(user.getAracSahibi().getBakiye() - fiyat);
            userRepository.save(user);

            return "Geçiş başarılı. Ücret: " + fiyat + " TL";
        } else {
            return "Yetersiz bakiye";
        }
    }

    private double hesaplaFiyat(Gise ilkGise, Gise sonGise) {
        // Basit bir fiyat hesaplama, iki gişe arasındaki fark
        // Örnek: her gişe arası 10 TL
        return Math.abs(ilkGise.getId() - sonGise.getId()) * 10;
    }
}
