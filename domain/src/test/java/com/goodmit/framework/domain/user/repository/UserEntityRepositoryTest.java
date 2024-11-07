package com.goodmit.framework.domain.user.repository;

import com.goodmit.framework.domain.config.DomainConfig;
import com.goodmit.framework.domain.user.base.BaseTest;
import com.goodmit.framework.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class UserRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository UserRepository;

    @Test
    void insertUser() {
        User User = User.builder()
                .name("test")
                .username("test")
                .password("test")
                .email("test@test.com")
                .build();

        User savedUser = UserRepository.save(User);

        assertNotNull(savedUser.getId());
        assertEquals(User.getName(), savedUser.getName());
        assertEquals(User.getUsername(), savedUser.getUsername());
        assertEquals(User.getPassword(), savedUser.getPassword());
        assertEquals(User.getEmail(), savedUser.getEmail());
    }

}