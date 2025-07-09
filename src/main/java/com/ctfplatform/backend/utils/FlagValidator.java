package com.ctfplatform.backend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class FlagValidator {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean matches(String rawFlag, String hashedFlag) {
        return encoder.matches(rawFlag, hashedFlag);
    }
}
