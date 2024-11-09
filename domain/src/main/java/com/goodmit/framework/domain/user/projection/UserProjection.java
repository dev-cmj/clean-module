package com.goodmit.framework.domain.user.projection;

import com.goodmit.framework.domain.role.RoleProjection;

import java.util.List;

public record UserProjection(
        Long id,
        String username,
        String password,
        String email,
        String name,
        List<RoleProjection> roles
) {
}
