package com.vouchers.dto.certification;

import java.util.UUID;

public record CertificationSearchResultDTO(
        UUID id,
        String code
) {
}
