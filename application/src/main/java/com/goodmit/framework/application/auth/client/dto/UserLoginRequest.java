package com.goodmit.framework.application.auth.client.dto;

import com.goodmit.framework.domain.user.entity.SocialType;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public record UserLoginRequest(
        String redirectUri,
        SocialType socialType
) {
}