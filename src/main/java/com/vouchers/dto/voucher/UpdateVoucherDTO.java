package com.vouchers.dto.voucher;

import com.vouchers.model.enums.Status;

public record UpdateVoucherDTO(
        Status status
) {
}
