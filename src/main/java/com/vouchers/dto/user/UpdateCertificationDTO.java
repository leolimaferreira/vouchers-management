package com.vouchers.dto.user;

import jakarta.validation.constraints.Size;

public record UpdateCertificationDTO(
        @Size(min = 6, max = 6, message = "Code must be 6 characters")
        String code
) {
}
