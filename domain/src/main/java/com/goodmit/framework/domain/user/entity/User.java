package com.goodmit.framework.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String socialId;

    private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Builder
    public static User of(String socialId, String email, String name, SocialType socialType) {
        return User.builder()
                .socialId(socialId)
                .email(email)
                .name(name)
                .socialType(socialType)
                .build();
    }

}
