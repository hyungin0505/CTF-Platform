package com.ctfplatform.backend.domain.challenge.dto;

public record ChallengeListResponse(
        Long id,
        String title,
        String category,
        String difficulty,
        Integer points,
        Integer chance
) {}