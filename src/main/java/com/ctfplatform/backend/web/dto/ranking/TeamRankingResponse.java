package com.ctfplatform.backend.web.dto.ranking;

public record TeamRankingResponse(
        Long teamId,
        String name,
        Integer points,
        String country,
        String link
) { }
