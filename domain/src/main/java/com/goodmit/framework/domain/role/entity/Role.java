package com.goodmit.framework.domain.role.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // ROLE_USER, ROLE_ADMIN 등의 역할 이름

    // getter, setter
    public static Role of(String name) {
        return Role.builder()
                .name(name)
                .build();
    }
}