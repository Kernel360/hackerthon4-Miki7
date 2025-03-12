package com.example.miki7.user.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    String username(String username);

    Optional<UserEntity> findUserEntityByUsername(String username);

    Optional<UserEntity> findByNickname(String nickname);

    boolean existsByUsername(String username);
}
