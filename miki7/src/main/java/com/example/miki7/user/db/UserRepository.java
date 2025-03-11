package com.example.miki7.user.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Object findByNickname(String nickname);

    Optional<Object> findByUsername(String username);
}
