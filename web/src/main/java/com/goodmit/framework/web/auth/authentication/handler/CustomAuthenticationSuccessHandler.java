package com.goodmit.framework.web.auth.authentication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodmit.framework.domain.user.entity.User;
import com.goodmit.framework.web.auth.jwt.JwtCookieManager;
import com.goodmit.framework.web.auth.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Principal;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;
    private final JwtProvider jwtProvider;
    private final JwtCookieManager jwtCookieManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        String user = (String) authentication.getPrincipal();

        response.setStatus(OK.value());
        response.setContentType(APPLICATION_JSON_VALUE);

        String token = jwtProvider.createToken(user);
        jwtCookieManager.addTokenToCookie(response, token);
        objectMapper.writeValue(response.getWriter(), token);
    }
}