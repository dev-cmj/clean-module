package com.goodmit.framework.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodmit.framework.web.auth.authentication.filter.CustomAuthenticationFilter;
import com.goodmit.framework.web.auth.authentication.handler.CustomAuthenticationFailureHandler;
import com.goodmit.framework.web.auth.authentication.handler.CustomAuthenticationSuccessHandler;
import com.goodmit.framework.web.auth.authentication.manager.CustomAuthenticationManager;
import com.goodmit.framework.web.auth.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationFilter authenticationFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // 필요 시 CSRF 설정 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**", "/error").permitAll() // 공개 URL 접근 허용
                        .anyRequest().authenticated() // 그 외 모든 요청은 인증 요구
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationManager(authenticationManager) // 커스텀 인증 매니저 설정
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable) // 필요 시 폼 로그인 설정 비활성화
                .logout(AbstractHttpConfigurer::disable) // 필요 시 로그아웃 설정 비활성화
        ;

        return http.build();
    }


}
