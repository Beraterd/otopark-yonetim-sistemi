package com.example.Hgs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Hgs.exception.CustomExceptions;
import com.example.Hgs.exception.CustomExceptions.AracSahibiNotFoundException;
import com.example.Hgs.model.AppUser;
import com.example.Hgs.model.AracSahibi;
import com.example.Hgs.model.Gecis;
import com.example.Hgs.model.Gise;
import com.example.Hgs.repository.AracSahibiRepository;
import com.example.Hgs.repository.GecisRepository;
import com.example.Hgs.repository.GiseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private  AracSahibiRepository aracSahibiRepository;
    private  GiseRepository giseRepository;
    private  GecisRepository gecisRepository;
    private UserService userService;  // Kullanıcıları almak için UserService'i inject et


    // Geçiş ekle
    public Gecis addGecis(Gecis gecis) {
        return gecisRepository.save(gecis);
    }

    // Gişe ücreti güncelle
    public Gise updateGisePrice(Long giseId, double newPrice) {
        Gise gise = giseRepository.findById(giseId)
                .orElseThrow(() -> new CustomExceptions.GiseNotFoundException("Gise bulunamadı"));
        gise.setGecisUcreti(newPrice);
        return giseRepository.save(gise);
    }

    // Kullanıcıların araç sahiplerini listele
    public List<AracSahibi> getAllAracSahipleri() {
        return aracSahibiRepository.findAll();
    }

    // Araç sahibi güncelle (Bakiye güncelleme)
    public AracSahibi updateAracSahibi(Long aracSahibiId, double yeniBakiye) {
        AracSahibi aracSahibi = aracSahibiRepository.findById(aracSahibiId)
                .orElseThrow(() -> new CustomExceptions.AracSahibiNotFoundException("Araç sahibi bulunamadı"));
        aracSahibi.setBakiye(yeniBakiye);
        return aracSahibiRepository.save(aracSahibi);
    }

    // Tüm kullanıcıları getir
    public List<AracSahibi> getAllUsers() {
        return aracSahibiRepository.findAll();
    }

    public AracSahibi aracSahibiGuncelle(Long aracSahibiId, double yeniBakiye) {
        AracSahibi aracSahibi = aracSahibiRepository.findById(aracSahibiId)
                .orElseThrow(() -> new RuntimeException("Araç sahibi bulunamadı"));
        aracSahibi.setBakiye(yeniBakiye);
        return aracSahibiRepository.save(aracSahibi);
    }

    public Gecis gecisEkle(String plaka, String ilkGise, String sonGise, double ucret, AppUser user) {
        // Araç sahibini buluyoruz
        AracSahibi aracSahibi = aracSahibiRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Araç sahibi bulunamadı"));

        // İlk Gişe'yi buluyoruz
        Gise giseIlk = giseRepository.findByAd(ilkGise)
                .orElseThrow(() -> new RuntimeException("İlk Gişe bulunamadı"));

        // Son Gişe'yi buluyoruz
        Gise giseSon = giseRepository.findByAd(sonGise)
                .orElseThrow(() -> new RuntimeException("Son Gişe bulunamadı"));

        // Geçişi oluşturuyoruz
        Gecis gecis = new Gecis();
        gecis.setPlaka(plaka);          // Araç plakası
        gecis.setIlkGise(giseIlk);          // İlk Gişe
        gecis.setSonGise(giseSon);          // Son Gişe
        gecis.setUcret(ucret);              // Ücret
        gecis.setAracSahibi(aracSahibi);    // Araç sahibini ilişkilendiriyoruz

        // Geçişi kaydediyoruz
        return gecisRepository.save(gecis);
    }

	public Object giseUcretGuncelle(Long id, double yeniUcret) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object tumKullanicilar() {
		// TODO Auto-generated method stub
		return null;
	}

    

}
