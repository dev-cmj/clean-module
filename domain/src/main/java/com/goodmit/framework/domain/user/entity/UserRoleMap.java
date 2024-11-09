package com.goodmit.framework.domain.user.entity;

import com.goodmit.framework.domain.role.entity.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_role_map")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRoleMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // getter, setter
    public static UserRoleMap of(User user, Role role) {
        return UserRoleMap.builder()
                .user(user)
                .role(role)
                .build();
    }
}