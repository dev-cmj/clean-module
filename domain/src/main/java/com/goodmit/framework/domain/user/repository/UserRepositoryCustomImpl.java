package com.goodmit.framework.domain.user.repository;

import com.goodmit.framework.domain.role.RoleProjection;
import com.goodmit.framework.domain.role.entity.QRole;
import com.goodmit.framework.domain.user.entity.User;
import com.goodmit.framework.domain.user.projection.UserProjection;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.goodmit.framework.domain.role.entity.QRole.role;
import static com.goodmit.framework.domain.user.entity.QUser.user;
import static com.goodmit.framework.domain.user.entity.QUserRoleMap.userRoleMap;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Optional<UserProjection> findByUsernameWithRoles(String username) {
        List<Tuple> results = jpaQueryFactory
                .select(user.id, user.username, user.password, user.email, user.name, role.id, role.name)
                .from(user)
                .leftJoin(userRoleMap).on(userRoleMap.user.id.eq(user.id))
                .leftJoin(role).on(userRoleMap.role.id.eq(role.id))
                .where(user.username.eq(username))
                .fetch();

        if (results.isEmpty()) {
            return Optional.empty();
        }

        // 첫 번째 Tuple에서 UserProjection 생성
        Tuple firstResult = results.getFirst();
        UserProjection userProjection = new UserProjection(
                firstResult.get(user.id),
                firstResult.get(user.username),
                firstResult.get(user.password),
                firstResult.get(user.email),
                firstResult.get(user.name),
                results.stream()
                        .map(tuple -> new RoleProjection(tuple.get(role.id), tuple.get(role.name)))
                        .collect(Collectors.toSet()) // Set으로 중복 Role 제거
                        .stream().toList() // List로 변환
        );

        return Optional.of(userProjection);
    }
}
