package com.ctfplatform.backend.domain.challenge.service;

import com.ctfplatform.backend.domain.challenge.Challenge;
import com.ctfplatform.backend.domain.challenge.dto.ChallengeDetailResponse;
import com.ctfplatform.backend.domain.challenge.dto.ChallengeListResponse;
import com.ctfplatform.backend.domain.challenge.dto.HintResponse;
import com.ctfplatform.backend.domain.challenge.repository.ChallengeRepository;
import com.ctfplatform.backend.exception.BaseException;
import com.ctfplatform.backend.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    public List<ChallengeListResponse> getAllChallenges() {
        return challengeRepository.findAll().stream()
                .map(ch -> new ChallengeListResponse(
                        ch.getId(),
                        ch.getTitle(),
                        ch.getCategory().name(),
                        ch.getDifficulty().name(),
                        ch.getPoints(),
                        ch.getTry_chance()
                ))
                .toList();
    }

    public ChallengeDetailResponse getChallengeDetail(Long id) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow( () -> new BaseException(ErrorCode.CHALLENGE_NOT_FOUND));

        return new ChallengeDetailResponse(
                challenge.getId(),
                challenge.getTitle(),
                challenge.getDescription(),
                challenge.getCategory().name(),
                challenge.getDifficulty().name(),
                challenge.getPoints(),
                challenge.getTry_chance(),
                challenge.getOpen_time(),
                challenge.getClose_time(),

                challenge.getChallenge_author().stream()
                        .map(author -> author.getUser().getNickname())
                        .toList(),
                challenge.getChallengeServer().stream()
                        .map(server -> server.getUrl())
                        .toList(),
                challenge.getChallengeFile().stream()
                        .map(file -> file.getUrl())
                        .toList(),
                challenge.getChallengeHint().stream()
                        .map(hint -> new HintResponse(hint.getHint(), hint.getPoints()))
                        .toList()
        );

    }
}
