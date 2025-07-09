package com.ctfplatform.backend.domain.challenge.dto;

import com.ctfplatform.backend.domain.challenge.ChallengeAuthor;
import com.ctfplatform.backend.domain.challenge.ChallengeFile;
import com.ctfplatform.backend.domain.challenge.ChallengeHint;
import com.ctfplatform.backend.domain.challenge.ChallengeServer;

import java.time.LocalDateTime;
import java.util.List;

public record ChallengeDetailResponse(
        Long id,
        String title,
        String description,
        String category,
        String difficulty,
        Integer points,
        Integer tryChance,
        LocalDateTime openTime,
        LocalDateTime closeTime,
        List<String> authors,
        List<String> servers,
        List<String> files,
        List<HintResponse> hints
) {
}
