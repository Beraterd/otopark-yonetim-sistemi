package com.example.Hgs.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Gise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String giseAdi;

    @Column(unique = true, nullable = false)
    private String giseKodu;

    private double gecisUcreti;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGiseAdi() {
		return giseAdi;
	}

	public void setGiseAdi(String giseAdi) {
		this.giseAdi = giseAdi;
	}

	public String getGiseKodu() {
		return giseKodu;
	}

	public void setGiseKodu(String giseKodu) {
		this.giseKodu = giseKodu;
	}

	public double getGecisUcreti() {
		return gecisUcreti;
	}

	public void setGecisUcreti(double gecisUcreti) {
		this.gecisUcreti = gecisUcreti;
	}
    
    
}
