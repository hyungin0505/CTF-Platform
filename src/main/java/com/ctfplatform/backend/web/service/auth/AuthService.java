package com.ctfplatform.backend.web.service.auth;

import com.ctfplatform.backend.domain.user.repository.UserRepository;
import com.ctfplatform.backend.domain.enums.Country;
import com.ctfplatform.backend.domain.user.User;
import com.ctfplatform.backend.exception.BaseException;
import com.ctfplatform.backend.exception.ErrorCode;
import com.ctfplatform.backend.utils.JwtProvider;
import com.ctfplatform.backend.web.dto.auth.LoginRequest;
import com.ctfplatform.backend.web.dto.auth.LoginResponse;
import com.ctfplatform.backend.web.dto.auth.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BaseException(ErrorCode.ALREADY_REGISTERED);
        }

        User user = User.builder()
                .email(request.email())
                .passwordHash(passwordEncoder.encode(request.password()))
                .nickname(request.nickname())
                .country(Country.valueOf(request.country()))
                .points(0)
                .build();

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new BaseException(ErrorCode.INVALID_PASSWORD);
        }

        String token = jwtProvider.generateToken(user.getId());

        return new LoginResponse(token);
    }
}
