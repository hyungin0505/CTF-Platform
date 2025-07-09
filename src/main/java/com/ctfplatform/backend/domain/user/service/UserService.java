package com.ctfplatform.backend.domain.user.service;

import com.ctfplatform.backend.domain.enums.Country;
import com.ctfplatform.backend.domain.user.User;
import com.ctfplatform.backend.domain.user.dto.UserInfoResponse;
import com.ctfplatform.backend.domain.user.repository.UserRepository;
import com.ctfplatform.backend.exception.BaseException;
import com.ctfplatform.backend.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfoResponse getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        return new UserInfoResponse(
                user.getId(),
                user.getNickname(),
                user.getEmail(),
                user.getCountry() != null ? Country.valueOf(user.getCountry().name()) : null,
                user.getRole() != null ? user.getRole().name() : null,
                user.getTeam() != null ? user.getTeam().getName() : null,
                user.getPoints()
        );
    }
}
