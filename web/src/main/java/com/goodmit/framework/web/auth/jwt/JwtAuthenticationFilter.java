package com.goodmit.framework.web.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final JwtCookieManager jwtCookieManager;
    private final JwtProperties jwtProperties;

    //필터 제외 (/api/login)
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/api/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = jwtCookieManager.getTokenFromCookie(request);

            // 쿠키 유효성 체크 후 SecurityContext에 인증정보 저장하기.
            log.info("JWT Token: {}", token);

        } catch (Exception e) {
            log.error("JWT 인증 오류", e);
        }

        filterChain.doFilter(request, response);
    }
}
