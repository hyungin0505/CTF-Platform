package com.ctfplatform.backend.domain.user.dto;

import com.ctfplatform.backend.domain.enums.Country;

public record UserInfoResponse(
        Long id,
        String nickname,
        Country country,
        String role,
        String teamName,
        Integer points
) { }
