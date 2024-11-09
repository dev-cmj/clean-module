package com.goodmit.framework.domain.user.repository;

import com.goodmit.framework.domain.role.entity.Role;
import com.goodmit.framework.domain.role.repository.RoleRepository;
import com.goodmit.framework.domain.user.base.BaseTest;
import com.goodmit.framework.domain.user.entity.User;
import com.goodmit.framework.domain.user.projection.UserProjection;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@Transactional
class UserRepositoryTest extends BaseTest {

    @BeforeEach
    void setup() {
        UserRepository.deleteAll();

        Role role = Role.of("ROLE_USER");
        Role role2 = Role.of("ROLE_ADMIN");

        roleRepository.save(role);
        roleRepository.save(role2);

        User user = User.of("test", "test", "test", "test");
        user.addRole(role);
        user.addRole(role2);
        UserRepository.save(user);

        User user2 = User.of("test2", "test2", "test2", "test2");
        user2.addRole(role);
        UserRepository.save(user2);
    }

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findUser() {
        UserProjection findUser = UserRepository.findByUsernameWithRoles("test").orElse(null);

        assertNotNull(findUser);
        assertEquals(findUser.username(), "test");
        assertEquals(findUser.roles().size(), 2);
        assertEquals(findUser.roles().get(0).name(), "ROLE_USER");
        assertEquals(findUser.roles().get(1).name(), "ROLE_ADMIN");
    }

}