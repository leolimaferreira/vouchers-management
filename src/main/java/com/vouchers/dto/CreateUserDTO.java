package com.vouchers.dto;

import com.vouchers.model.enums.Role;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        Role role
) {
}
