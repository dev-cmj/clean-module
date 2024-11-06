package com.goodmit.framework.domain.user.repository;

import com.goodmit.framework.domain.config.DomainConfig;
import com.goodmit.framework.domain.user.base.BaseTest;
import com.goodmit.framework.domain.user.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class UserEntityRepositoryTest extends BaseTest {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Test
    void insertUser() {
        UserEntity userEntity = UserEntity.builder()
                .name("test")
                .username("test")
                .password("test")
                .email("test@test.com")
                .build();

        UserEntity savedUser = userEntityRepository.save(userEntity);

        assertNotNull(savedUser.getId());
        assertEquals(userEntity.getName(), savedUser.getName());
        assertEquals(userEntity.getUsername(), savedUser.getUsername());
        assertEquals(userEntity.getPassword(), savedUser.getPassword());
        assertEquals(userEntity.getEmail(), savedUser.getEmail());
    }

}