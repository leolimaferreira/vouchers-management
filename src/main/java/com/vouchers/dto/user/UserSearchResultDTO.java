package com.vouchers.dto.user;

import java.util.UUID;

public record UserSearchResultDTO(
        UUID id,
        String email,
        String role
) {
}
