package com.ctfplatform.backend.web.dto.ranking;

import com.ctfplatform.backend.domain.enums.Country;

public record TeamRankingResponse(
        Long teamId,
        String name,
        Integer points,
        Country country,
        String link
) { }
