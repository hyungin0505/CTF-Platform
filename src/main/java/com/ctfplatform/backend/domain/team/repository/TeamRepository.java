package com.ctfplatform.backend.domain.team.repository;

import com.ctfplatform.backend.domain.team.Team;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {
    List<Team> findAllByOrderByPointsDesc();
}
