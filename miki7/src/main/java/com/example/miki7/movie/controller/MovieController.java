package com.example.miki7.movie.controller;

import com.example.miki7.movie.db.MovieEntity;
import com.example.miki7.movie.model.MovieDto;
import com.example.miki7.movie.model.SearchDto;
import com.example.miki7.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping(value = "/search")
    public String search(@ModelAttribute SearchDto searchDto, Model model) {
        log.info("{}",searchDto);

        List<MovieEntity> movies = movieService.findMoviesByKeyword(searchDto.getKeyword());

        model.addAttribute("movies",movies);

        return "result";
    }
}
