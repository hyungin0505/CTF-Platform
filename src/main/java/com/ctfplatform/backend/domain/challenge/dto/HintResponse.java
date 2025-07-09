package com.ctfplatform.backend.domain.challenge.dto;


public record HintResponse(
        String hint,
        Integer points
) { }
