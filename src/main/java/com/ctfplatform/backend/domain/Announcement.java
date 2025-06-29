package com.ctfplatform.backend.domain;

import com.ctfplatform.backend.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Announcement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String title;

    @Lob
    private String content;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isPinned = false;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isHidden = false;

}
