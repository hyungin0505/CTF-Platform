package com.ctfplatform.backend.domain.challenge.repository;

import com.ctfplatform.backend.domain.challenge.ChallengeSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeSubmissionRepository extends JpaRepository<ChallengeSubmission, Long> {
}
