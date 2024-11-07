package com.goodmit.framework.application.auth.social.service;

import com.goodmit.framework.application.auth.social.dto.UserInfoResponse;
import com.goodmit.framework.application.auth.social.dto.UserLoginRequest;
import kr.goodmit.framework.common.enums.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class KakaoSocialService implements SocialService {

    @Override
    public UserInfoResponse login(String authorizationCode, UserLoginRequest loginRequest) {
        // Kakao login logic
        return new UserInfoResponse("KAKAO_ID", SocialType.KAKAO, "user@example.com");
    }

    @Override
    public void revokeUser(String authorizationCode, UserLoginRequest userLoginRequest) {
        // Kakao revoke user logic
    }

    @Override
    public SocialType getSocialType() {
        return SocialType.KAKAO;
    }
}
