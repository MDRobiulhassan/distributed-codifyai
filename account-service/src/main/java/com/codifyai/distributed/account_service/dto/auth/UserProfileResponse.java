package com.codifyai.distributed.account_service.dto.auth;

public record UserProfileResponse(
        Long id,
        String username,
        String name
) {
}
