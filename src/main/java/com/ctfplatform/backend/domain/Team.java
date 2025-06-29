package com.ctfplatform.backend.domain;

import com.ctfplatform.backend.domain.common.BaseEntity;
import com.ctfplatform.backend.domain.enums.Country;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 50)
    private String link;

    @Lob
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private Country country;

    @Column(nullable = false, length = 16)
    private String invite_token;

    @Builder.Default
    private Integer points = 0;

}