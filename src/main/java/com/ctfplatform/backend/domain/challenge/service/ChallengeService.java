package com.ctfplatform.backend.domain.challenge.service;

import com.ctfplatform.backend.domain.challenge.Challenge;
import com.ctfplatform.backend.domain.challenge.ChallengeSubmission;
import com.ctfplatform.backend.domain.challenge.SolveLog;
import com.ctfplatform.backend.domain.challenge.dto.ChallengeDetailResponse;
import com.ctfplatform.backend.domain.challenge.dto.ChallengeListResponse;
import com.ctfplatform.backend.domain.challenge.dto.HintResponse;
import com.ctfplatform.backend.domain.challenge.repository.ChallengeRepository;
import com.ctfplatform.backend.domain.challenge.repository.ChallengeSubmissionRepository;
import com.ctfplatform.backend.domain.challenge.repository.SolveLogRepository;
import com.ctfplatform.backend.domain.challenge.repository.UserRepository;
import com.ctfplatform.backend.domain.team.repository.TeamRepository;
import com.ctfplatform.backend.domain.user.User;
import com.ctfplatform.backend.exception.BaseException;
import com.ctfplatform.backend.exception.ErrorCode;
import com.ctfplatform.backend.utils.FlagValidator;
import com.ctfplatform.backend.web.dto.challenge.FlagSubmitRequest;
import com.ctfplatform.backend.web.dto.challenge.FlagSubmitResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final FlagValidator flagValidator;
    private final SolveLogRepository solveLogRepository;
    private final ChallengeSubmissionRepository challengeSubmissionRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public List<ChallengeListResponse> getAllChallenges() {
        return challengeRepository.findAll().stream()
                .map(ch -> new ChallengeListResponse(
                        ch.getId(),
                        ch.getTitle(),
                        ch.getCategory().name(),
                        ch.getDifficulty().name(),
                        ch.getPoints(),
                        ch.getChance()
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
                challenge.getChance(),
                challenge.getOpenTime(),
                challenge.getCloseTime(),

                challenge.getChallengeAuthor().stream()
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

    @Transactional
    public FlagSubmitResultResponse submitFlag(Long challengeId, Long userId, FlagSubmitRequest request) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new BaseException(ErrorCode.CHALLENGE_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow( () -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if (solveLogRepository.existsByUserIdAndChallengeId(userId, challengeId)) {
            throw new BaseException(ErrorCode.ALREADY_SOLVED);
        }

        boolean isCorrect = flagValidator.matches(request.flag(), challenge.getFlagHash());

        ChallengeSubmission submission = ChallengeSubmission.builder()
                .challenge(challenge)
                .user(user)
                .flagHash(request.flag())
                .correct(isCorrect)
                .build();
        challengeSubmissionRepository.save(submission);

        if (isCorrect) {
            SolveLog solveLog = SolveLog.builder()
                    .challenge(challenge)
                    .user(user)
                    .team(user.getTeam())
                    .build();
            solveLogRepository.save(solveLog);

            user.addPoints(challenge.getPoints());
            userRepository.save(user);

            user.getTeam().addPoints(challenge.getPoints());
            teamRepository.save(user.getTeam());
        }

        return new FlagSubmitResultResponse(
                isCorrect,
                isCorrect ? "정답" : "오답",
                isCorrect ? challenge.getPoints() : 0
        );
    }
}
