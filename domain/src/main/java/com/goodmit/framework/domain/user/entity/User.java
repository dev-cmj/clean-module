package com.goodmit.framework.domain.user.entity;

import jakarta.persistence.*;
import kr.goodmit.framework.common.enums.SocialType;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static User of(String socialId, String email, String name, SocialType socialType) {
        return User.builder()
                .socialId(socialId)
                .email(email)
                .name(name)
                .socialType(socialType)
                .build();
    }

}
