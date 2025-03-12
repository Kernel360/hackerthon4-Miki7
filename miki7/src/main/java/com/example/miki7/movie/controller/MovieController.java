package com.example.miki7.movie.controller;

import com.example.miki7.actor.db.ActorEntity;
import com.example.miki7.actor.service.ActorService;
import com.example.miki7.cast.db.CastEntity;
import com.example.miki7.cast.service.CastService;
import com.example.miki7.movie.db.MovieEntity;
import com.example.miki7.movie.model.DetailSearchDto;
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
    private final ActorService actorService;
    private final CastService castService;

    // 통합 검색 - 키워드를 가지는 영화,배우,배역을 반환
    @PostMapping(value = "/search")
    public String search(@ModelAttribute SearchDto searchDto, Model model) {

        List<MovieEntity> movies = movieService.findMoviesByKeyword(searchDto.getKeyword());
        List<ActorEntity> actors = actorService.findActorsByKeyword(searchDto.getKeyword());
        List<CastEntity> casts = castService.findCastsByKeyword(searchDto.getKeyword());

        model.addAttribute("movies", movies);
        model.addAttribute("actors", actors);
        model.addAttribute("casts", casts);

        return "result";
    }

    // 세부 사항 검색 1 - 배우의 이름을 입력받고 출연 작품을 검색
    // 세부 사항 검색 2 - 배역을 입력받고, 해당 배역을 연기한 배우와 영화를 검색
    // 세부 사항 검색 3 - 배우를 입력 받고, 장르를 선택
    // 세부 사항 검색 4 - 배우를 입력 받고, 연도를 선택
    @PostMapping("/search/detail")
    public String detailSearch(@ModelAttribute DetailSearchDto detailSearchDto, Model model) {
        List<MovieEntity> movies = movieService.detailSearchMovies(detailSearchDto);
        model.addAttribute("movies", movies);
        return "result"; // 검색 결과를 표시할 템플릿 페이지
    }


    @GetMapping(value = "/result/{id}")
    public String detail(@PathVariable Long id, Model model) {

        MovieEntity movie = movieService.findMovieById(id);

        model.addAttribute("movie", movie);

        return "post";
    }

}
