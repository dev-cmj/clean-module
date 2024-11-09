package com.goodmit.framework.domain.user.entity;

import com.goodmit.framework.domain.role.entity.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Users")
@ToString(exclude = {"roles", "password"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<UserRoleMap> roles = new HashSet<>();

    public static User of(String username, String password, String email, String name) {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }

    public void addRole(Role role) {
        UserRoleMap userRoleMap = UserRoleMap.of(this, role);
        roles.add(userRoleMap);
    }
}
