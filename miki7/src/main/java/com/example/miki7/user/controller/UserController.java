package com.example.miki7.user.controller;

import com.example.miki7.user.model.UserRequest;
import com.example.miki7.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "")
    public String create(
            @RequestBody UserRequest userRequest, Model model
    ) {
        userService.create(userRequest);

        return "redirect:/login";
    }

    // 회원 탈퇴
    @PutMapping(value = "")
    public void delete() {

//        return
    }

    // 회원 정보 단건 조회
//    @GetMapping(value = "/id/{id}")
//    public UserDto findOne(@PathVariable Long id) {
//
//        return userService.findOne(id);
//    }

    // 전체 유저 정보 조회
//    @GetMapping(value = "/all")
//    public List<UserDto> findAll() {
//
//        return
//    }

    // 내 정보 수정
//    @PutMapping(value = "/id/{id}")
//    public UserDto update(){
//
//        return
//    }


}

