package com.ctfplatform.backend.web.controller;

import com.ctfplatform.backend.domain.challenge.dto.ChallengeDetailResponse;
import com.ctfplatform.backend.domain.challenge.dto.ChallengeListResponse;
import com.ctfplatform.backend.domain.challenge.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {
    private final ChallengeService challengeService;

    @GetMapping
    public List<ChallengeListResponse> getChallenges() {
        return challengeService.getAllChallenges();
    }

    @GetMapping("/{id}")
    public ChallengeDetailResponse getChallenge(@PathVariable Long id) {
        return challengeService.getChallengeDetail(id);
    }
}
