package com.ctfplatform.backend.domain.team.service;

import com.ctfplatform.backend.domain.challenge.repository.UserRepository;
import com.ctfplatform.backend.domain.enums.Country;
import com.ctfplatform.backend.domain.enums.Role;
import com.ctfplatform.backend.domain.team.Team;
import com.ctfplatform.backend.domain.team.dto.TeamCreateRequest;
import com.ctfplatform.backend.domain.team.dto.TeamResponse;
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
}
