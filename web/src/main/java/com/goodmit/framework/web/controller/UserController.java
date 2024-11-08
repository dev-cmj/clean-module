package com.goodmit.framework.web.controller;

import com.goodmit.framework.application.auth.social.dto.UserInfoResponse;
import com.goodmit.framework.application.auth.social.dto.UserLoginRequest;
import com.goodmit.framework.application.auth.social.service.SocialService;
import com.goodmit.framework.application.auth.social.service.SocialServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static kr.goodmit.framework.common.enums.SocialType.GOOGLE;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final MessageSource messageSource;
    private final SocialServiceManager socialServiceManager;

//
//    @PostMapping("/login")
//    public ResponseEntity<UserInfoResponse> login(
//            @RequestParam String authorizationCode,
//            @RequestBody UserLoginRequest loginRequest
//    ) {
//        SocialService socialService = socialServiceManager.getSocialService(loginRequest.socialType());
//        UserInfoResponse userInfo = socialService.login(authorizationCode, loginRequest);
//        return ResponseEntity.ok(userInfo);
//    }

}
