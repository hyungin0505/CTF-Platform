package com.ctfplatform.backend.web.controller.challenge;

import com.ctfplatform.backend.domain.challenge.dto.ChallengeDetailResponse;
import com.ctfplatform.backend.domain.challenge.dto.ChallengeListResponse;
import com.ctfplatform.backend.domain.challenge.service.ChallengeService;
import com.ctfplatform.backend.web.dto.challenge.FlagSubmitRequest;
import com.ctfplatform.backend.web.dto.challenge.FlagSubmitResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{id}/submit")
    public FlagSubmitResultResponse submitFlag(
            @PathVariable Long id,
            @RequestBody FlagSubmitRequest request,
            @AuthenticationPrincipal UserPrincipal user // 로그인 사용자
    ) {
        return challengeService.submitFlag(id, user.getId(), request);
    }
}
