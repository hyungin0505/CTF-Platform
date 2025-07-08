package com.ctfplatform.backend.web.controller;

import com.ctfplatform.backend.domain.challenge.service.RankingService;
import com.ctfplatform.backend.web.dto.ranking.TeamRankingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/challenges")
public class RankingController {
    private final RankingService rankingService;

    @GetMapping("/ranking")
    public ResponseEntity<List<TeamRankingResponse>> getTeamRanking() {
        return ResponseEntity.ok(rankingService.getTeamRanking());
    }
}
