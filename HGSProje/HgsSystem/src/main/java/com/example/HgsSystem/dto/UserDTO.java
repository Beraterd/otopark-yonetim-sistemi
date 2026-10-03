package com.example.HgsSystem.dto;

import java.util.Objects;

public class UserDTO {
    private Long id;
    private String username;
    private String role;

    // Getter ve Setter metodları
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // toString, equals, hashCode metodları
    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // Eğer aynı nesne ise
        if (o == null || getClass() != o.getClass()) return false; // Null kontrolü ve class kontrolü
        UserDTO userDTO = (UserDTO) o;  // Object -> UserDTO dönüşümü
        return Objects.equals(id, userDTO.id) &&  // id karşılaştırması
               Objects.equals(username, userDTO.username) &&  // username karşılaştırması
               Objects.equals(role, userDTO.role);  // role karşılaştırması
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, role);  // id, username ve role'u kullanarak hash kodu oluşturuluyor
    }
}
