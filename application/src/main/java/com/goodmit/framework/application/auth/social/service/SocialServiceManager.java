package com.goodmit.framework.application.auth.social.service;

import kr.goodmit.framework.common.enums.SocialType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SocialServiceManager {

    private final Map<SocialType, SocialService> socialServiceMap;

    public SocialServiceManager(List<SocialService> socialServices) {
        this.socialServiceMap = initializeServiceMap(socialServices);
    }

    private Map<SocialType, SocialService> initializeServiceMap(List<SocialService> services) {
        return services.stream()
                .collect(Collectors.toMap(SocialService::getSocialType, Function.identity(), (existing, replacement) -> existing, () -> new EnumMap<>(SocialType.class)));
    }

    public SocialService getSocialService(SocialType socialType) {
        return Optional.ofNullable(socialServiceMap.get(socialType))
                .orElseThrow(() -> {
                    log.error("지원되지 않는 소셜 타입 요청: {}", socialType);
                    return new IllegalArgumentException("지원되지 않는 소셜 타입입니다: " + socialType);
                });
    }
}