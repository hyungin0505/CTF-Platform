package com.ctfplatform.backend.web.controller;

import com.ctfplatform.backend.domain.team.dto.TeamCreateRequest;
import com.ctfplatform.backend.domain.team.dto.TeamJoinRequest;
import com.ctfplatform.backend.domain.team.dto.TeamResponse;
import com.ctfplatform.backend.domain.team.dto.TeamStatsResponse;
import com.ctfplatform.backend.domain.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/{id}")
    public ResponseEntity<TeamStatsResponse> getTeamStats(@PathVariable("id") Long id) {
        return ResponseEntity.ok(teamService.getTeamStats(id));
    }

    @PostMapping("/create")
    public ResponseEntity<TeamResponse> createTeam(
            @RequestBody TeamCreateRequest request,
            @RequestParam Long userId
            ) {
        return ResponseEntity.ok(teamService.createTeam(userId, request));
    }

    @PostMapping("/join")
    public ResponseEntity<TeamResponse> joinTeam(
            @RequestBody TeamJoinRequest request,
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(teamService.joinTeam(userId, request.inviteToken()));
    }
}
