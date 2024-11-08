package com.goodmit.framework.web.auth.authentication.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(value = "auth.provider.db", havingValue = "true", matchIfMissing = true)
@Slf4j
public class DBAuthenticationProvider extends ChainableAuthenticationProvider {


    @Override
    protected Authentication doAuthenticate(Authentication authentication) throws AuthenticationException {
        log.info("DB 인증 시작");
        // DB 인증 로직
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 예시: DB 서버에 실제로 인증 로직을 추가
        boolean isAuthenticated = authenticateWithDB(username, password);

        if (isAuthenticated) {
            log.info("DB 인증 성공: {}", username);
            return new UsernamePasswordAuthenticationToken(username, password, getAuthorities(username));
        }
        return null; // 인증 실패 시 null 반환하여 다음 Provider로 넘어감
    }

    private boolean authenticateWithDB(String username, String password) {
        // DB 서버에 실제로 인증 로직을 추가
        return true;
    }

    private List<GrantedAuthority> getAuthorities(String username) {
        // 사용자의 권한을 조회하여 반환
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
