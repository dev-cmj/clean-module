package com.goodmit.framework.application.auth.client.dto;

import com.goodmit.framework.domain.user.entity.SocialType;

public record UserInfoResponse(
        String socialId,
        SocialType socialType,
        String email
) {
    public static UserInfoResponse of(
            final String socialId,
            final SocialType socialType,
            final String email
    ) {
        return new UserInfoResponse(socialId, socialType, email);
    }
}