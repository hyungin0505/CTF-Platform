package com.ctfplatform.backend.domain.team.service;

import com.ctfplatform.backend.domain.challenge.repository.ChallengeSubmissionRepository;
import com.ctfplatform.backend.domain.challenge.repository.SolveLogRepository;
import com.ctfplatform.backend.domain.user.repository.UserRepository;
import com.ctfplatform.backend.domain.enums.Country;
import com.ctfplatform.backend.domain.enums.Role;
import com.ctfplatform.backend.domain.team.Team;
import com.ctfplatform.backend.domain.team.dto.TeamCreateRequest;
import com.ctfplatform.backend.domain.team.dto.TeamResponse;
import com.ctfplatform.backend.domain.team.dto.TeamStatsResponse;
import com.ctfplatform.backend.domain.team.repository.TeamRepository;
import com.ctfplatform.backend.domain.user.User;
import com.ctfplatform.backend.exception.BaseException;
import com.ctfplatform.backend.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final SolveLogRepository solveLogRepository;
    private final ChallengeSubmissionRepository challengeSubmissionRepository;

    @Transactional
    public TeamResponse createTeam(Long userId, TeamCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if (user.getTeam() != null) {
            throw new BaseException(ErrorCode.ALREADY_IN_TEAM);
        }

        String token = UUID.randomUUID().toString().substring(0, 16);

        Team team = Team.builder()
                .name(request.name())
                .link(request.link())
                .description(request.description())
                .country(Country.valueOf(request.country()))
                .inviteToken(token)
                .points(0)
                .build();

        teamRepository.save(team);

        user.setTeam(team);
        user.setRole(Role.LEADER);
        userRepository.save(user);

        return new TeamResponse(
                team.getId(),
                team.getName(),
                team.getLink(),
                team.getDescription(),
                team.getCountry(),
                team.getInviteToken()
        );
    }

    @Transactional
    public TeamResponse joinTeam(Long userId, String inviteToken) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if (user.getTeam() != null) {
            throw new BaseException(ErrorCode.ALREADY_IN_TEAM);
        }

        Team team = teamRepository.findByInviteToken(inviteToken)
                .orElseThrow(() -> new BaseException(ErrorCode.TEAM_NOT_FOUND));

        user.setTeam(team);
        user.setRole(Role.MEMBER);
        userRepository.save(user);

        return new TeamResponse(
                team.getId(),
                team.getName(),
                team.getLink(),
                team.getDescription(),
                team.getCountry(),
                team.getInviteToken()
        );
    }

    @Transactional
    public TeamStatsResponse getTeamStats(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new BaseException(ErrorCode.TEAM_NOT_FOUND));

        String orgName = team.getOrganization() != null ? team.getOrganization().getName() : null;

        int totalSolves = solveLogRepository.countByTeamId(teamId);
        int totalSubmissions = challengeSubmissionRepository.countByTeamId(teamId);
        int correctSubmissions = challengeSubmissionRepository.countByTeamIdAndCorrectTrue(teamId);
        double accuracy = totalSubmissions > 0 ? (double) correctSubmissions / (double) totalSubmissions : 0.0;

        return new TeamStatsResponse(
                team.getId(),
                team.getName(),
                team.getCountry(),
                team.getLink(),
                team.getDescription(),
                team.getPoints(),
                orgName,
                totalSolves,
                totalSubmissions,
                correctSubmissions,
                accuracy
        );

    }

}
