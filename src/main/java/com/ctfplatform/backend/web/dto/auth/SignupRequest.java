package com.ctfplatform.backend.web.dto.auth;

public record SignupRequest(
        String email,
        String password,
        String nickname,
        String name,
        String country,
        String birth
) { }
