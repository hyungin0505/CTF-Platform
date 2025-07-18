package com.ctfplatform.backend.web.controller;

import com.ctfplatform.backend.domain.organization.Organization;
import com.ctfplatform.backend.domain.organization.dto.OrganizationCreateRequest;
import com.ctfplatform.backend.domain.organization.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping("/create")
    public ResponseEntity<Organization> create(@RequestBody OrganizationCreateRequest request) {
        Organization created = organizationService.create(request);
        return ResponseEntity.ok(created);
    }
}
