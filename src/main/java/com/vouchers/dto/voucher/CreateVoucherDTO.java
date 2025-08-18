package com.vouchers.dto.voucher;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateVoucherDTO(
        @NotBlank(message = "Supplier is required")
        @Size(min = 3, max = 100, message = "Supplier name must be between 3 and 100 characters")
        String supplier,
        @NotNull(message = "Certification is required")
        UUID certificationId,
        @NotNull(message = "Value is required")
        @DecimalMin(value = "0.01", message = "Value must be greater than 0.01")
        BigDecimal value,
        @NotNull(message = "Rules are required")
        List<String> rules
) {
}
