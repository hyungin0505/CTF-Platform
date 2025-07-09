package com.ctfplatform.backend.web.dto.auth;

public record LoginRequest(
        String email,
        String password
) { }
