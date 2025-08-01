package com.ctfplatform.backend.domain.challenge.dto;

import java.time.LocalDateTime;

public record ChallengeCreateRequest(
        String title,
        String description,
        String category,
        String difficulty,
        String flag,
        Integer points,
        Integer chance,
        LocalDateTime openTime,
        LocalDateTime closeTime
) { }