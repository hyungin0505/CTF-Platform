package com.ctfplatform.backend.web.dto.challenge;

public record FlagSubmitResultResponse(
        boolean correct,
        String message,
        int earnedPoints
) {
}
