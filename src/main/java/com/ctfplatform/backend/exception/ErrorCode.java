package com.ctfplatform.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    CHALLENGE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 문제를 찾을 수 없습니다."),
    ALREADY_SOLVED(HttpStatus.BAD_REQUEST, "이미 푼 문제입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    ALREADY_IN_TEAM(HttpStatus.CONFLICT, "이미 팀에 속해있습니다."),
    TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, "팀이 존재하지 않습니다."),
    ALREADY_REGISTERED(HttpStatus.CONFLICT, "이미 회원가입된 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 잘못되었습니다."),

    ;

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}