package com.goodmit.framework.domain.user.repository;

import com.goodmit.framework.domain.user.entity.User;
import com.goodmit.framework.domain.user.projection.UserProjection;

import java.util.Optional;

public interface UserRepositoryCustom {

    //모든 사용자의 정보(권한 포함)를 조회하는 메서드
    Optional<UserProjection> findByUsernameWithRoles(String username);
}
