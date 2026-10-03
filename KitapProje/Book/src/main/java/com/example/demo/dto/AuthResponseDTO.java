package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;

    public AuthResponseDTO() {}

    public AuthResponseDTO(String token) {
        this.token = token;
    }

    // Getter ve Setter

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
