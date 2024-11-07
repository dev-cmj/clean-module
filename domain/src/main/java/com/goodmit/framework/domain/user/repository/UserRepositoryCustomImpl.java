package com.goodmit.framework.domain.user.repository;

import com.goodmit.framework.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.goodmit.framework.common.enums.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<User> findBySocialTypeAndSocialId(String socialId, SocialType socialType) {
        return Optional.empty();
    }
}
