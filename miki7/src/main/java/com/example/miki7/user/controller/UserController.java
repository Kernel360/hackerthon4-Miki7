package com.example.miki7.user.controller;

import com.example.miki7.user.db.UserEntity;
import com.example.miki7.user.model.UserDto;
import com.example.miki7.user.model.UserRequest;
import com.example.miki7.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "")
    public String create(@ModelAttribute UserRequest userRequest, Model model) {
        log.info("Received UserRequest: {}", userRequest);

        userService.create(userRequest);

        return "redirect:/login";
    }

    // 회원 탈퇴
    @PutMapping(value = "/id/{id}")
    public String delete(@PathVariable Long id) {
        UserDto userDto = userService.findById(id);
        userService.delete(id);

        var updatedUserEntity = UserEntity.builder()
                .id(id)
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .birthDate(userDto.getBirthDate())
                .gender(userDto.getGender())
                .profileImage(userDto.getProfileImage())
                .createdAt(userDto.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .deletedAt(LocalDateTime.now())
                .roles(userDto.getRoles())
                .status("UNREGISTERED")
                .build();

//        userService.create(updatedUserEntity);

        return "redirect:/";
    }


    // 내 정보 수정
//    @PutMapping(value = "/id/{id}")
//    public UserDto update(){
//
//        return
//    }


}

