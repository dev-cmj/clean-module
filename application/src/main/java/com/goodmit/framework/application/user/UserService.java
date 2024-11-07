package com.goodmit.framework.application.user;

import com.goodmit.framework.application.user.dto.UserRequest;
import com.goodmit.framework.application.user.dto.UserResponse;
import com.goodmit.framework.domain.user.entity.UserEntity;
import com.goodmit.framework.domain.user.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    public UserResponse signUp(UserRequest userRequest) {
        UserEntity userEntity = toEntity(userRequest);
        return toResponse(userEntityRepository.save(userEntity));
    }

    public UserResponse signIn(UserRequest userRequest) {
        UserEntity userEntity = toEntity(userRequest);
        userEntityRepository.findByUsername(userEntity.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return toResponse(userEntity);
    }

    private UserEntity toEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .username(userRequest.username())
                .password(userRequest.password())
                .name(userRequest.name())
                .email(userRequest.email())
                .build();
    }

    private UserResponse toResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .username(userEntity.getUsername())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }


}
