package com.ctfplatform.backend.domain.team.dto;

public record TeamResponse(
        Long id,
        String name,
        String link,
        String description,
        com.ctfplatform.backend.domain.enums.Country country,
        String inviteToken
) { }
