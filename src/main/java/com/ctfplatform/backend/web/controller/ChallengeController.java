package com.ctfplatform.backend.web.controller;

import com.ctfplatform.backend.domain.challenge.dto.ChallengeCreateRequest;
import com.ctfplatform.backend.domain.challenge.dto.ChallengeDetailResponse;
import com.ctfplatform.backend.domain.challenge.dto.ChallengeListResponse;
import com.ctfplatform.backend.domain.challenge.service.ChallengeService;
import com.ctfplatform.backend.domain.user.User;
import com.ctfplatform.backend.web.dto.challenge.FlagSubmitRequest;
import com.ctfplatform.backend.web.dto.challenge.FlagSubmitResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {
    private final ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<Void> createChallenge(@RequestBody ChallengeCreateRequest request,
                                                @AuthenticationPrincipal User user) {
        challengeService.createChallenge(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<ChallengeListResponse> getChallenges() {
        return challengeService.getAllChallenges();
    }

    @GetMapping("/{id}")
    public ChallengeDetailResponse getChallenge(@PathVariable Long id) {
        return challengeService.getChallengeDetail(id);
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<FlagSubmitResultResponse> submitFlag(
            @PathVariable Long id,
            @RequestBody FlagSubmitRequest request,
            @RequestParam Long userId // 로그인 사용자
    ) {
        return ResponseEntity.ok(challengeService.submitFlag(id, userId, request));
    }
}
