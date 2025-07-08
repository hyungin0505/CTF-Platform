package com.ctfplatform.backend.domain.challenge.repository;

import com.ctfplatform.backend.domain.challenge.SolveLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolveLogRepository extends JpaRepository<SolveLog, Long> {
    boolean existsByUserIdAndChallengeId(Long userId, Long challengeId);
}
