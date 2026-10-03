package com.example.Hgs.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Hgs.model.AppUser;
import com.example.Hgs.model.AracSahibi;
import com.example.Hgs.model.Gecis;
import com.example.Hgs.model.Gise;
import com.example.Hgs.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    // Geçiş ekle
    @PostMapping("/gecis")
    public ResponseEntity<Gecis> gecisEkle(
            @RequestParam String plaka,
            @RequestParam String ilkGise,
            @RequestParam String sonGise,
            @RequestParam double ucret,
            @RequestBody AppUser user) {
        return ResponseEntity.ok(adminService.gecisEkle(plaka, ilkGise, sonGise, ucret, user));
    }
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    // Gişe ücreti güncelle
    @PutMapping("/gise/{id}")
    public ResponseEntity<Gise> giseGuncelle(@PathVariable Long id, @RequestParam double yeniUcret) {
    	return ResponseEntity.ok(gise); // Gise nesnesi döndürdüğünüzden emin olun

    }

    // Tüm kullanıcıları getir
    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> tumKullanicilar() {
    	return ResponseEntity.ok(appUsers);
    }

    // Araç sahibi güncelle
    @PutMapping("/arac/{id}")
    public ResponseEntity<AracSahibi> aracSahibiGuncelle(@PathVariable Long id, @RequestParam double yeniBakiye) {
        return ResponseEntity.ok(adminService.aracSahibiGuncelle(id, yeniBakiye));
    }
}
