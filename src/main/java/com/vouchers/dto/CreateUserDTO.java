package com.vouchers.dto;

import com.vouchers.model.enums.Role;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(
        @NotBlank
        @Min(15)
        @Max(150)
        String name,
        @NotBlank
        @Min(15)
        @Max(255)
        String email,
        @NotBlank
        @Min(8)
        @Max(20)
        String password,
        @NotNull
        Role role
) {
}
