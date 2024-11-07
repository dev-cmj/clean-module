package com.goodmit.framework.application.auth.social.service;

import com.goodmit.framework.application.auth.social.dto.UserInfoResponse;
import com.goodmit.framework.application.auth.social.dto.UserLoginRequest;
import kr.goodmit.framework.common.enums.SocialType;
import org.springframework.stereotype.Service;

import static kr.goodmit.framework.common.enums.SocialType.GITHUB;

@Service
public class GitHubSocialService implements SocialService {


    @Override
    public UserInfoResponse login(String authorizationCode, UserLoginRequest loginRequest) {
        return UserInfoResponse.of("GITHUB_ID", GITHUB, "test@github.com");
    }

    @Override
    public void revokeUser(String authorizationCode, UserLoginRequest userLoginRequest) {
        // GitHub revoke user logic
    }

    @Override
    public SocialType getSocialType() {
        return GITHUB;
    }
}
