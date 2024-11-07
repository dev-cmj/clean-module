package com.goodmit.framework.application.auth.social.service;

import com.goodmit.framework.application.auth.social.dto.UserInfoResponse;
import com.goodmit.framework.application.auth.social.dto.UserLoginRequest;
import kr.goodmit.framework.common.enums.SocialType;

public interface SocialService {

    UserInfoResponse login(final String authorizationCode, final UserLoginRequest loginRequest);

    default UserInfoResponse getLoginDto(final SocialType socialType, final String clientId, final String email) {
        return UserInfoResponse.of(clientId, socialType, email);
    }

    void revokeUser(final String authorizationCode, final UserLoginRequest userLoginRequest);

    SocialType getSocialType();

}
