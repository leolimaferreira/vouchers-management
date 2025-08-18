package com.vouchers.dto.voucher;

import com.vouchers.model.Certification;
import com.vouchers.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record VoucherSearchResultDTO(
        UUID id,
        String supplier,
        Certification certification,
        BigDecimal value,
        LocalDate expirationDate,
        List<String> rules,
        Status status
) {
}
