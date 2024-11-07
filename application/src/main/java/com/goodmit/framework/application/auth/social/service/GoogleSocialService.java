package com.goodmit.framework.application.auth.social.service;

import com.goodmit.framework.application.auth.social.dto.UserInfoResponse;
import com.goodmit.framework.application.auth.social.dto.UserLoginRequest;
import kr.goodmit.framework.common.enums.SocialType;
import org.springframework.stereotype.Service;

@Service
public class GoogleSocialService implements SocialService {

    @Override
    public UserInfoResponse login(String authorizationCode, UserLoginRequest loginRequest) {
        // Google login logic
        return new UserInfoResponse("GOOGLE_ID", SocialType.GOOGLE, "user@example.com");
    }

    @Override
    public void revokeUser(String authorizationCode, UserLoginRequest userLoginRequest) {
        // Google revoke user logic
    }

    @Override
    public SocialType getSocialType() {
        return SocialType.GOOGLE;
    }
}