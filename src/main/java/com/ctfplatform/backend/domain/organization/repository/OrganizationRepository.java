package com.ctfplatform.backend.domain.organization.repository;

import com.ctfplatform.backend.domain.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    boolean existsByName(String name);
}
