package com.example.miki7.user.controller;

import com.example.miki7.user.model.UserDto;
import com.example.miki7.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    // 회원 정보 단건 조회
    @GetMapping(value = "/id/{id}")
    public UserDto findById(@PathVariable Long id) {

        return userService.findById(id);
    }

    // 전체 유저 정보 조회
    @GetMapping(value = "/all")
    public List<UserDto> findAll() {

        return userService.findAll();
    }

}
