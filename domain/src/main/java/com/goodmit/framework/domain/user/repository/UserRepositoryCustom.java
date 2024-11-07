package com.goodmit.framework.domain.user.repository;

import com.goodmit.framework.domain.user.entity.User;
import kr.goodmit.framework.common.enums.SocialType;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findBySocialTypeAndSocialId(final String socialId, final SocialType socialType);
}
