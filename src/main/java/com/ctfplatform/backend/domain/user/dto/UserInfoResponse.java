package com.ctfplatform.backend.domain.user.dto;

import com.ctfplatform.backend.domain.enums.Country;
import com.ctfplatform.backend.domain.user.User;

public record UserInfoResponse(
        Long id,
        String nickname,
        Country country,
        String role,
        String teamName,
        Integer points
) {
    public static UserInfoResponse from(User user) {
        return new UserInfoResponse(
                user.getId(),
                user.getNickname(),
                user.getCountry(),
                user.getRole().name(),
                user.getTeam() != null ? user.getTeam().getName() : null,
                user.getPoints()
        );
    }
}
