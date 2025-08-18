package com.vouchers.dto.certification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCertificationDTO(
        @NotBlank(message = "Code is required")
        @Size(min = 6, max = 6, message = "Code must be 6 characters")
        String code
) {
}
