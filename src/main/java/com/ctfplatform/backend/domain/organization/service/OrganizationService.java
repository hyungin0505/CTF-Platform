package com.ctfplatform.backend.domain.organization.service;

import com.ctfplatform.backend.domain.organization.Organization;
import com.ctfplatform.backend.domain.organization.dto.OrganizationCreateRequest;
import com.ctfplatform.backend.domain.organization.repository.OrganizationRepository;
import com.ctfplatform.backend.exception.BaseException;
import com.ctfplatform.backend.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Organization create(OrganizationCreateRequest request) {
        if (organizationRepository.existsByName(request.name())) {
            throw new BaseException(ErrorCode.DUPLICATE_ORGANIZATION);
        }

        Organization org = Organization.builder()
                .name(request.name())
                .type(request.type())
                .build();

        return organizationRepository.save(org);
    }
}
