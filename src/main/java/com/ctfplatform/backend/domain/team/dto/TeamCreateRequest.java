package com.ctfplatform.backend.domain.team.dto;

public record TeamCreateRequest (
    String name,
    String link,
    String description,
    String country
){ }