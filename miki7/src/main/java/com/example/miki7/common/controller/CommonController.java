package com.example.miki7.common.controller;

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

    @GetMapping(value="/register")
    public String register(Model model) {
        return "register";
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
