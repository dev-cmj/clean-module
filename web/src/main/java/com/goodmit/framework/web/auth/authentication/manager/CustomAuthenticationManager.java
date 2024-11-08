package com.goodmit.framework.web.auth.authentication.manager;

import com.goodmit.framework.web.auth.authentication.provider.ChainableAuthenticationProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Component
@Slf4j
public class CustomAuthenticationManager implements AuthenticationManager {

    private final List<ChainableAuthenticationProvider> authChain;

    public CustomAuthenticationManager(List<ChainableAuthenticationProvider> authChain) {
        this.authChain = authChain;
        for (int i = 0; i < authChain.size() - 1; i++) {
            authChain.get(i).linkWith(authChain.get(i + 1));
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Authentication mainAuthResult = null;
        for (ChainableAuthenticationProvider provider : authChain) {
            mainAuthResult = provider.authenticate(authentication);
            if (mainAuthResult == null || !mainAuthResult.isAuthenticated()) {
                throw new AuthenticationException("Main authentication failed in provider: " + provider.getClass().getSimpleName()) {
                };
            }
        }

        return mainAuthResult; // 모든 인증 성공 시 최종 인증 반환
    }
}