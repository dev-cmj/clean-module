package com.goodmit.framework.web.auth.authentication.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodmit.framework.web.auth.authentication.dto.LoginRequest;
import com.goodmit.framework.web.auth.authentication.handler.CustomAuthenticationFailureHandler;
import com.goodmit.framework.web.auth.authentication.handler.CustomAuthenticationSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.HttpMethod.POST;

@Component
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager,
                                      ObjectMapper objectMapper,
                                      CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
                                      CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        // 특정 URL과 HTTP 메서드로 필터 동작 설정
        super(new AntPathRequestMatcher("/api/login", "POST"));
        this.setAuthenticationManager(authenticationManager);
        this.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        this.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (!request.getMethod().equals(POST.name())) {
            throw new IllegalStateException("Authentication method not supported: " + request.getMethod());
        }

        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);
            loginRequest.validate();

            String username = loginRequest.username();
            String password = loginRequest.password();

            // UsernamePasswordAuthenticationToken을 생성합니다.
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (IOException e) {
            throw new IllegalArgumentException("알 수 없는 요청입니다.");
        }
    }
}
