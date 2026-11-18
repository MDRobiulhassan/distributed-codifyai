package com.codifyai.distributed.account_service.dto.subscription;

public record UsageTodayResponse(
        Integer tokenUsed,
        Integer tokenLimits,
        Integer previewsRunning,
        Integer previewsLimit
) {
}
