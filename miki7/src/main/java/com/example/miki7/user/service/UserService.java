package com.example.miki7.user.service;


import com.example.miki7.user.db.UserEntity;
import com.example.miki7.user.db.UserRepository;
import com.example.miki7.user.model.UserDto;
import com.example.miki7.user.model.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    public UserDto create(UserRequest userRequest) {

        // username 중복 체크
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다: " + userRequest.getUsername());
        }

        Set<String> roles = new HashSet<>();
        roles.add("USER");

        var entity = UserEntity.builder()
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .nickname(userRequest.getNickname())
                .birthDate(LocalDateTime.now())
                .gender(userRequest.getGender())
                .profileImage(userRequest.getProfileImage())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .deletedAt(null)
                .roles(roles)
                .status("REGISTERED")
                .build();

        var saveEntity = userRepository.save(entity);

        return userConverter.toDto(saveEntity);
    }

    public UserDto findById(Long id) {
        var entity = userRepository.findById(id);

        return userConverter.toDto(entity.get());
    }

    public Optional<UserEntity> findByUsername(String username){

        return userRepository.findUserEntityByUsername(username);
    }

    public List<UserDto> findAll(){

        return userRepository.findAll().stream().map(userConverter::toDto).toList();
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public void update(UserEntity userEntity) {

        userRepository.save(userEntity);
    }

    public Optional<UserEntity> findByNickname(String username) {
        return userRepository.findByNickname(username);
    }
}