package com.goodmit.framework.application.auth.social.dto;


import kr.goodmit.framework.common.enums.SocialType;

public record UserLoginRequest(
        String redirectUri,
        SocialType socialType
) {
}