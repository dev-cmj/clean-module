package com.goodmit.framework.application.auth.client.service;

import com.goodmit.framework.application.auth.client.dto.UserInfoResponse;
import com.goodmit.framework.application.auth.client.dto.UserLoginRequest;
import com.goodmit.framework.domain.user.entity.SocialType;

public interface SocialService {

    UserInfoResponse login(final String authorizationCode, final UserLoginRequest loginRequest);

    default UserInfoResponse getLoginDto(final SocialType socialType, final String clientId, final String email) {
        return UserInfoResponse.of(clientId, socialType, email);
    }

    void revokeUser(final String authorizationCode, final UserLoginRequest userLoginRequest);

    SocialType getSocialType();

}
