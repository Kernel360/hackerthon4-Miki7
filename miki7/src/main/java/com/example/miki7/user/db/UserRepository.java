package com.example.miki7.user.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<Object> findByNickname(String nickname);

}
