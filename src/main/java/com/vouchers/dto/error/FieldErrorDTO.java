package com.vouchers.dto.error;

public record FieldErrorDTO(
        String field,
        String error
) {
}
