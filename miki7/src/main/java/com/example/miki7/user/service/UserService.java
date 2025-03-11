package com.example.miki7.user.service;


import com.example.miki7.user.db.UserEntity;
import com.example.miki7.user.db.UserRepository;
import com.example.miki7.user.model.UserDto;
import com.example.miki7.user.model.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    public UserDto create(UserRequest userRequest) {

        Set<String> roles = new HashSet<>();
        roles.add("USER");

        var entity = UserEntity.builder()
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .nickname(userRequest.getNickname())
                .birthDate(userRequest.getBirthDate())
                .gender(userRequest.getGender())
                .profileImage(userRequest.getProfileImage())
                .createdAt(userRequest.getCreatedAt())
                .updatedAt(userRequest.getUpdatedAt())
                .deletedAt(userRequest.getDeletedAt())
                .roles(roles)
                .status(userRequest.getStatus())
                .build();

        var saveEntity = userRepository.save(entity);

        return userConverter.toDto(saveEntity);
    }

    public UserDto findOne(Long id) {
        var saveEntity = userRepository.findById(id);

        return userConverter.toDto(saveEntity.get());
    }
}