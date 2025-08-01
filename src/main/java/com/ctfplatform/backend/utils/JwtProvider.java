package com.ctfplatform.backend.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key secret_key = Keys.hmacShaKeyFor("{SECRET_KEY_32_bytes_AAAAAAAAAA}".getBytes());
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public String generateToken(Long userid) {
        return Jwts.builder()
                .setSubject(userid.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secret_key, SignatureAlgorithm.HS256)
                .compact();
    }
}
