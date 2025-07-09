package com.ctfplatform.backend.domain.challenge.repository;

import com.ctfplatform.backend.domain.challenge.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

}
