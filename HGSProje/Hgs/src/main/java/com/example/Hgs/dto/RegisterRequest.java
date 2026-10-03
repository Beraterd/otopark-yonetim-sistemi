package com.example.Hgs.dto;

import com.example.Hgs.model.Role;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank String username,
        @NotBlank String password,
        Role role
) {}
