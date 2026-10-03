package com.example.Hgs.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Gecis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plaka;
    private Gise ilkGise;
    private Gise sonGise;
    private double gecisUcreti;
    private LocalDateTime gecisTarihi;
    private boolean gecisDurumu;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaka() {
		return plaka;
	}

	public void setPlaka(String plaka) {
		this.plaka = plaka;
	}

	public Gise getIlkGise() {
		return ilkGise;
	}

	public void setilkGise(Gise ilkGise) {
		this.ilkGise = ilkGise;
	}

	public Gise getSonGise() {
		return sonGise;
	}

	public void setsonGise(Gise sonGise) {
		this.sonGise = sonGise;
	}

	public double getGecisUcreti() {
		return gecisUcreti;
	}

	public void setGecisUcreti(double gecisUcreti) {
		this.gecisUcreti = gecisUcreti;
	}

	public LocalDateTime getGecisTarihi() {
		return gecisTarihi;
	}

	public void setGecisTarihi(LocalDateTime gecisTarihi) {
		this.gecisTarihi = gecisTarihi;
	}

	public boolean isGecisDurumu() {
		return gecisDurumu;
	}

	public void setGecisDurumu(boolean gecisDurumu) {
		this.gecisDurumu = gecisDurumu;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public void setTarih(LocalDateTime now) {
		// TODO Auto-generated method stub
		
	}
	public void setUcret(double gecisUcreti) {
        this.gecisUcreti = gecisUcreti;
    }
    private AracSahibi aracSahibi;

	public void setAracSahibi(AracSahibi aracSahibi) {
        this.aracSahibi = aracSahibi;
    }
	 public void setIlkGise(Gise ilkGise) {
	        this.ilkGise = ilkGise;
	    }

	    public void setSonGise(Gise sonGise) {
	        this.sonGise = sonGise;
	    }
	    
    
}
