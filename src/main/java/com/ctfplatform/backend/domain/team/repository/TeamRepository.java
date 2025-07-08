package com.ctfplatform.backend.domain.team.repository;

import com.ctfplatform.backend.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByOrderByPointsDesc();
}
