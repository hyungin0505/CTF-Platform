package com.ctfplatform.backend.domain.challenge.service;

import com.ctfplatform.backend.domain.challenge.dto.ChallengeListResponse;
import com.ctfplatform.backend.domain.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    public List<ChallengeListResponse> getAllChallenges() {
        return challengeRepository.findAll().stream()
                .map(ch -> new ChallengeListResponse(
                        ch.getId(),
                        ch.getTitle(),
                        ch.getCategory().name(),
                        ch.getDifficulty().name(),
                        ch.getPoints(),
                        ch.getTry_chance()
                ))
                .toList();
    }

}
