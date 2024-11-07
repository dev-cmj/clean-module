package com.goodmit.framework.application.user.service;

import com.goodmit.framework.domain.user.entity.User;
import com.goodmit.framework.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository UserRepository;



}
