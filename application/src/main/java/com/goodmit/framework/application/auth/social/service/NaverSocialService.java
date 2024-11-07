package com.goodmit.framework.application.auth.social.service;

import com.goodmit.framework.application.auth.social.dto.UserInfoResponse;
import com.goodmit.framework.application.auth.social.dto.UserLoginRequest;
import kr.goodmit.framework.common.enums.SocialType;
import org.springframework.stereotype.Service;

import static kr.goodmit.framework.common.enums.SocialType.NAVER;

@Service

public class NaverSocialService implements SocialService {

    @Override
    public UserInfoResponse login(String authorizationCode, UserLoginRequest loginRequest) {
        return new UserInfoResponse("NAVER_ID", NAVER, "test@naver.com");
    }

    @Override
    public void revokeUser(String authorizationCode, UserLoginRequest userLoginRequest) {
        // Naver revoke user logic
    }

    @Override
    public SocialType getSocialType() {
        return NAVER;
    }
}
