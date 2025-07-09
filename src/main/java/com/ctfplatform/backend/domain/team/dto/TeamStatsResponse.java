package com.ctfplatform.backend.domain.team.dto;

import com.ctfplatform.backend.domain.enums.Country;

public record TeamStatsResponse(
        Long teamId,
        String name,
        Country country,
        String link,
        String description,
        int points,
        String organization,
        int totalSolves,
        int totalSubmissions,
        int correctSubmissions,
        double accuracy
) { }
