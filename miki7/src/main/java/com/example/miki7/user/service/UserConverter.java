package com.example.miki7.user.service;

import com.example.miki7.user.db.UserEntity;
import com.example.miki7.user.model.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    public UserDto toDto(UserEntity userEntity){
        return UserDto.builder()
                .id(userEntity.getId())
                .password(userEntity.getPassword())
                .nickname(userEntity.getNickname())
                .birthDate(userEntity.getBirthDate())
                .gender(userEntity.getGender())
                .profileImage(userEntity.getProfileImage())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .deletedAt(userEntity.getDeletedAt())
                .roles(userEntity.getRoles())
                .status(userEntity.getStatus())
                .build();
    }
}
