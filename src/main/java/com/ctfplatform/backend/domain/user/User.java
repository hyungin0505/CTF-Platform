package com.ctfplatform.backend.domain.user;

import com.ctfplatform.backend.domain.common.BaseEntity;
import com.ctfplatform.backend.domain.enums.Country;
import com.ctfplatform.backend.domain.enums.Role;
import com.ctfplatform.backend.domain.enums.Status;
import com.ctfplatform.backend.domain.team.Team;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String nickname;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private Country country;

    private LocalDate birth;

    @Setter
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false, length = 10)
    private Role role = Role.USER;

    @Column(length = 50)
    private String link;

    @Builder.Default
    @Column(nullable = false)
    private Integer points = 0;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false, length = 10)
    private Status status = Status.ACTIVE;

    @Column(nullable = false, length = 100)
    private String passwordHash;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team")
    private Team team;

    public void addPoints(int points) {
        this.points += points;
    }

}