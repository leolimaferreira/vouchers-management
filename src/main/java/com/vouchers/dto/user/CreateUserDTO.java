package com.vouchers.dto.user;

import com.vouchers.annotation.Password;
import com.vouchers.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,
        @NotBlank(message = "Password is required")
        @Password(message = "Password must be between 8 and 20 characters and contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
        String password,
        @NotNull(message = "Role is required")
        @com.vouchers.annotation.Role(message = "Role must be ADMIN, MANAGER or COLLABORATOR")
        Role role
) {
}
