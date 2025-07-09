package com.ctfplatform.backend.domain.challenge.service;

import com.ctfplatform.backend.domain.team.repository.TeamRepository;
import com.ctfplatform.backend.web.dto.ranking.TeamRankingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final TeamRepository teamRepository;

    public List<TeamRankingResponse> getTeamRanking() {
        return teamRepository.findAllByOrderByPointsDesc().stream()
                .map(team -> new TeamRankingResponse(
                        team.getId(),
                        team.getName(),
                        team.getPoints() == null ? 0 : team.getPoints(),
                        team.getCountry(),
                        team.getLink()
                ))
                .toList();
    }
}
