package com.ctfplatform.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    CHALLENGE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 문제를 찾을 수 없습니다."),

    ;

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}