package com.example.miki7.movie.controller;

import com.example.miki7.actor.db.ActorEntity;
import com.example.miki7.actor.service.ActorService;
import com.example.miki7.cast.db.CastEntity;
import com.example.miki7.cast.service.CastService;
import com.example.miki7.movie.db.MovieEntity;
import com.example.miki7.movie.model.DetailSearchDto;
import com.example.miki7.movie.model.SearchDto;
import com.example.miki7.movie.service.MovieService;
import com.example.miki7.review.db.ReviewEntity;
import com.example.miki7.review.model.ReviewDto;
import com.example.miki7.review.service.ReviewService;
import com.example.miki7.user.db.UserEntity;
import com.example.miki7.user.model.CustomUserDetails;
import com.example.miki7.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserService userService;
    private final ReviewService reviewService;

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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        Long userId = user.getId();
        UserEntity userEntity = userService.findByNickname(userDetails.getUsername()).get();
        Long userId = userEntity.getId();
        MovieEntity movie = movieService.findMovieById(id);
        List<CastEntity> castList = reviewService.getCastsByMovieId(id); // 해당영화 배역 목록
        List<ReviewDto> reviews = reviewService.getReviewsByMovieId(id); // ✅ 해당 영화의 리뷰 목록 추가
        int totalScore = 0;
        for(ReviewDto review:reviews){
            totalScore+=review.getReviewRating();
        }
        String averageScore = String.format("%.2f", Double.parseDouble(String.valueOf(totalScore)) / reviews.size());

        model.addAttribute("loginUserId",userId); // 모델에 userId 추가
        model.addAttribute("movie", movie); // ✅ 영화 정보 추가
        model.addAttribute("movieId", id); // 선택한 영화
        model.addAttribute("castList", castList); // 영화에 출연한 배우/배역 목록 선택을 위해
        model.addAttribute("review", new ReviewEntity());
        model.addAttribute("reviews", reviews); // ✅ 영화 리뷰 목록 추가
        model.addAttribute("avgScore",averageScore);

        return "post";
    }

}
