package com.example.miki7.user.controller;

import com.example.miki7.user.db.UserEntity;
import com.example.miki7.user.model.CustomUserDetails;
import com.example.miki7.user.model.UserDto;
import com.example.miki7.user.model.UserRequest;
import com.example.miki7.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String delete(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
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

        userService.update(updatedUserEntity);

        logoutUser(request, response);

        return "redirect:/";
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) {
        // 현재 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 인증 정보 제거
        SecurityContextHolder.clearContext();

        // 쿠키 제거 (예: JSESSIONID)
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0); // 쿠키 만료 시간 설정
        cookie.setPath("/");
        response.addCookie(cookie);
    }


    // 내 정보 수정
    @GetMapping(value = "/mypage")
    public String update(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        log.info("{}",userDetails.getUsername());
        log.info("{}",userDetails.getPassword());
        log.info("{}",userDetails.getAuthorities());
        return "redirect:/mypage";
    }


}

