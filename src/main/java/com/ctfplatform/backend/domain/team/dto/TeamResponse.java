package com.ctfplatform.backend.domain.team.dto;

import com.ctfplatform.backend.domain.enums.Country;

public record TeamResponse(
        Long id,
        String name,
        String link,
        String description,
        Country country,
        String inviteToken
) { }
