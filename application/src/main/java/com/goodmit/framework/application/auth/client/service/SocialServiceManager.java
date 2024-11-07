package com.goodmit.framework.application.auth.client.service;

import com.goodmit.framework.domain.user.entity.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SocialServiceManager {

    private final Map<SocialType, SocialService> socialServiceMap;

    public SocialService getSocialService(final List<SocialType> socialTypes) {
        return socialTypes.stream()
                .map(socialServiceMap::get)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 소셜 타입입니다."));
    }

    public SocialService getSocialService(final SocialType socialType) {
        return socialServiceMap.get(socialType);
    }
}
