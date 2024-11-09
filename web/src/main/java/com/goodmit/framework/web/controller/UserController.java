package com.goodmit.framework.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final MessageSource messageSource;

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
