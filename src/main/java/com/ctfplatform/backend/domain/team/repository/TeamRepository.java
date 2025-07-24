package com.ctfplatform.backend.domain.team.repository;

import com.ctfplatform.backend.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByOrderByPointsDesc();
    Optional<Team> findByInviteToken(String inviteToken);
}
