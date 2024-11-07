package com.goodmit.framework.domain.user.repository;

import com.goodmit.framework.domain.user.base.BaseTest;
import com.goodmit.framework.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static kr.goodmit.framework.common.enums.SocialType.KAKAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class UserRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository UserRepository;

    @Test
    void insertUser() {
            User user = User.of("test", "test", "test", KAKAO);

        User savedUser = UserRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getEmail(), savedUser.getEmail());
    }

}