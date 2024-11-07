package com.goodmit.framework.domain.user.repository;

import com.goodmit.framework.domain.user.entity.SocialType;
import com.goodmit.framework.domain.user.entity.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findBySocialTypeAndSocialId(final String socialId, final SocialType socialType);
}
