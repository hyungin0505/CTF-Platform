package com.ctfplatform.backend.domain;

import com.ctfplatform.backend.domain.common.BaseEntity;
import com.ctfplatform.backend.domain.enums.Type;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Organization extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Type type;

}
