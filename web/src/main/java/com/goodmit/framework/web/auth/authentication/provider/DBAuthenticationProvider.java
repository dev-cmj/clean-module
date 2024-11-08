package com.goodmit.framework.web.auth.authentication.provider;

import com.goodmit.framework.web.auth.authentication.service.DbUserDetailsService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(value = "auth.provider.db", havingValue = "true", matchIfMissing = true)
@Slf4j
@RequiredArgsConstructor
public class DBAuthenticationProvider extends ChainableAuthenticationProvider {

    private final DbUserDetailsService dbUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        setPriority(1); // 첫 번째로 실행되도록 설정
    }

    @Override
    protected Authentication doAuthenticate(Authentication authentication) throws AuthenticationException {
        log.info("DB 인증 시작");

        String username = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        // DB에서 사용자 조회
        UserDetails userDetails = authenticateWithDB(username);

        if (userDetails != null && passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
            log.info("DB 인증 성공: {}", username);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }

        return null;
    }

    private UserDetails authenticateWithDB(String username) {
        return dbUserDetailsService.loadUserByUsername(username);
    }

    private List<GrantedAuthority> getAuthorities(String username) {
        // 필요시 권한을 조회해 반환
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}