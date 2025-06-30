package com.ctfplatform.backend.domain.challenge;

import com.ctfplatform.backend.domain.common.BaseEntity;
import com.ctfplatform.backend.domain.enums.Category;
import com.ctfplatform.backend.domain.enums.Difficulty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Challenge extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Difficulty difficulty;

    @Column(nullable = false, length = 100, unique = true)
    private String flag_hash;

    private Integer points;

    private Integer try_chance;

    private LocalDateTime open_time;
    private LocalDateTime close_time;

    @OneToMany(mappedBy = "challenge", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ChallengeAuthor> challenge_author = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ChallengeServer> challengeServer = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ChallengeFile> challengeFile = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ChallengeHint> challengeHint = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<TryChance> tryChance = new ArrayList<>();

    @OneToMany(mappedBy = "challenge")
    private List<SolveLog> solveLog = new ArrayList<>();

    @OneToMany(mappedBy = "challenge")
    private List<ChallengeSubmission> challengeSubmission = new ArrayList<>();

}