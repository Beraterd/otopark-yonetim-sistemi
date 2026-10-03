package com.example.Hgs.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AracSahibi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isim;
    private double bakiye;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public double getBakiye() {
		return bakiye;
	}

	public void setBakiye(double bakiye) {
		this.bakiye = bakiye;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}
    
    
}
