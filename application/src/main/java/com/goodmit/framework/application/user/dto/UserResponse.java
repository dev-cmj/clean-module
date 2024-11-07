package com.goodmit.framework.application.user.dto;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String username,
        String name,
        String email
) {
}
