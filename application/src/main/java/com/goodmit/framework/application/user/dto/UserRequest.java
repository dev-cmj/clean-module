package com.goodmit.framework.application.user.dto;

import com.goodmit.framework.domain.user.entity.UserEntity;

public record UserRequest(
        String username,
        String password,
        String name,
        String email
) {
}
