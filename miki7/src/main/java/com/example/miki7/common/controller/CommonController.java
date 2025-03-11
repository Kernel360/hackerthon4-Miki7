package com.example.miki7.common.controller;

import com.example.miki7.user.model.UserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping(value="")
    public String home(Model model){
        return "index";
    }

    // 커스텀 로그인페이지
    @GetMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "register"; // Thymeleaf 템플릿 이름
    }

    @GetMapping("/author")
    public String myPage(Model model) {
        model.addAttribute("title", "My Page Title");
        model.addAttribute("authorName", "John Doe");
        model.addAttribute("authorDescription", "I love coding.");
//        model.addAttribute("posts", "Posts");

        return "author"; // author.html 템플릿 반환
    }
}
