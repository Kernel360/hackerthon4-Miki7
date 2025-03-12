package com.example.miki7.common.controller;

import com.example.miki7.movie.db.MovieEntity;
import com.example.miki7.movie.service.MovieService;
import com.example.miki7.review.db.ReviewEntity;
import com.example.miki7.review.service.ReviewService;
import com.example.miki7.user.db.UserEntity;
import com.example.miki7.user.model.CustomUserDetails;
import com.example.miki7.user.model.UserDto;
import com.example.miki7.user.model.UserRequest;
import com.example.miki7.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommonController {

    private final MovieService movieService;
    private final UserService userService;
    private final ReviewService reviewService;

    @GetMapping(value="")
    public String home(Model model){

        List<MovieEntity> movies = movieService.getAllMovies();

        model.addAttribute("movies",movies);

        return "index";
    }

    // 커스텀 로그인페이지
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "register"; // Thymeleaf 템플릿 이름
    }

    @GetMapping("/mypage")
    public String myPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Optional<UserEntity> userEntityOptional = userService.findByNickname(userDetails.getUsername());

        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            UserDto userDto = userEntity.toDto();
            model.addAttribute("user", userDto); // 사용자 정보 전달
            List<ReviewEntity> reviews = reviewService.findAllByUserId(userDto.getId());
            model.addAttribute("reviews", reviews); // 리뷰 정보 전달
        } else {
            // 기본값 설정 또는 에러 메시지 추가
            UserEntity defaultUser = new UserEntity();
            UserDto userDto = defaultUser.toDto();
            userDto.setProfileImage("/static/img/av.png"); // 기본 프로필 이미지
            userDto .setNickname("Guest"); // 기본 닉네임
            model.addAttribute("user", defaultUser);
            model.addAttribute("error", "사용자 정보를 찾을 수 없습니다."); // 에러 메시지 전달
        }

        return "author"; // author.html 템플릿 반환
    }



}
