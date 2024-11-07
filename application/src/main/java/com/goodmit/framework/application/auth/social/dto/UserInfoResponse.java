package com.goodmit.framework.application.auth.social.dto;


import kr.goodmit.framework.common.enums.SocialType;

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