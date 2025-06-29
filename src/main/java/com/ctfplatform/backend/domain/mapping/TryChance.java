package com.ctfplatform.backend.domain.mapping;

import com.ctfplatform.backend.domain.Challenge;
import com.ctfplatform.backend.domain.Team;
import com.ctfplatform.backend.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TryChance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Column(nullable = false)
    private Integer attempts = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge")
    private Challenge challenge;

}
