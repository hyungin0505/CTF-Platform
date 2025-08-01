package com.ctfplatform.backend.web.dto.auth;

import java.time.LocalDate;

public record SignupRequest(
        String email,
        String password,
        String nickname,
        String link,
        String name,
        String country,
        LocalDate birth
) { }
