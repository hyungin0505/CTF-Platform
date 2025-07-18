package com.ctfplatform.backend.domain.organization.dto;

import com.ctfplatform.backend.domain.enums.Type;

public record OrganizationCreateRequest(
        String name,
        Type type
) { }
