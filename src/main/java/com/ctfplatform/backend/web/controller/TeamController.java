package com.ctfplatform.backend.web.controller;

import com.ctfplatform.backend.domain.team.dto.TeamCreateRequest;
import com.ctfplatform.backend.domain.team.dto.TeamResponse;
import com.ctfplatform.backend.domain.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/create")
    public ResponseEntity<TeamResponse> createTeam(
            @RequestBody TeamCreateRequest request,
            @RequestParam Long userId
            ) {
        return ResponseEntity.ok(teamService.createTeam(userId, request));
    }
}
