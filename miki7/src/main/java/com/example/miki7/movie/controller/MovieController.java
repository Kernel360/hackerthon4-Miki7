package com.example.miki7.movie.controller;

import com.example.miki7.movie.db.MovieEntity;
import com.example.miki7.movie.model.SearchDto;
import com.example.miki7.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping(value = "/search")
    public String search(@ModelAttribute SearchDto searchDto, Model model) {

        List<MovieEntity> movies = movieService.findMoviesByKeyword(searchDto.getKeyword());

        model.addAttribute("movies", movies);

        return "result";
    }

    @GetMapping(value = "/result/{id}")
    public String detail(@PathVariable Long id, Model model) {

        MovieEntity movie = movieService.findMovieById(id);

        model.addAttribute("movie",movie);

        return "post";
    }

}
