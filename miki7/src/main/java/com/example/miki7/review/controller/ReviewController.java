package com.example.miki7.review.controller;

import com.example.miki7.movie.db.MovieEntity;
import com.example.miki7.movie.service.MovieService;
import com.example.miki7.review.db.ReviewEntity;
import com.example.miki7.review.model.ReviewDto;
import com.example.miki7.review.model.ReviewRequest;
import com.example.miki7.review.service.ReviewService;
import com.example.miki7.user.model.CustomUserDetails;
import com.example.miki7.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import com.example.miki7.cast.db.CastEntity;
import com.example.miki7.user.db.UserEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
 /*
     회원은 영화에 대한 리뷰를 생성할 수 있습니다.
     조건
     1. 회원만 리뷰 생성가능
     2. 선택한 영화에서만 리뷰 가능
     3. 리뷰
     3.1) 영화에 대한 리뷰
     3.2) 영화 배역에 대한 리뷰
  */

    private final ReviewService reviewService;
    private final MovieService movieService;
    private final UserService userService;

//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//    UserEntity userEntity = userService.findByNickname(userDetails.getUsername()).get();

    // 리뷰 작성 폼
    @GetMapping("/new/{movieId}")
    public String showReviewForm(@PathVariable Long movieId, Model model, HttpSession session) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        Long userId = user.getId();
        UserEntity userEntity = userService.findByNickname(userDetails.getUsername()).get();
        Long userId = userEntity.getId();
        MovieEntity movie = movieService.findMovieById(movieId); // ✅ 변경: MovieService에서 영화 정보 가져오기
        List<CastEntity> castList = reviewService.getCastsByMovieId(movieId); // 해당영화 배역 목록
        List<ReviewDto> reviews = reviewService.getReviewsByMovieId(movieId); // ✅ 해당 영화의 리뷰 목록 추가
        int totalScore = 0;
        for(ReviewDto review:reviews){
            totalScore+=review.getReviewRating();
        }
        String averageScore = String.format("%.2f", Double.parseDouble(String.valueOf(totalScore)) / reviews.size());

        model.addAttribute("loginUserId",userId); // 모델에 userId 추가
        model.addAttribute("movie", movie); // ✅ 영화 정보 추가
        model.addAttribute("movieId", movieId); // 선택한 영화
        model.addAttribute("castList", castList); // 영화에 출연한 배우/배역 목록 선택을 위해
        model.addAttribute("review", new ReviewEntity());
        model.addAttribute("reviews", reviews); // ✅ 영화 리뷰 목록 추가
        model.addAttribute("avgScore",averageScore);

        for(CastEntity cast :castList){
            log.info("cast: {}", cast.getCastName()); // ✅ castList 값 확인
        }

        //아이디 값 확인
        log.info("userId: {}", userId);


        return "review-form"; // 타임리프 템플릿 (@테스트 완료 바꾸기)
    }

    // 리뷰 저장 요청 처리
    @PostMapping("/save")
    public String saveReview(@ModelAttribute ReviewRequest reviewRequest, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.findByNickname(userDetails.getUsername()).get();
        Long userId = userEntity.getId();



        reviewService.saveReview(reviewRequest,userId);

        return "redirect:/reviews/new/" + reviewRequest.getMovieId(); // @영화 상세페이지 경로로 변경
    }


//    //u 회원은 영화에 대한 리뷰를 수정할 수 있습니다.
//    // ✅ 리뷰 수정 요청 처리
//    @PostMapping("/edit/{reviewId}")
//    public String showEditReview(@ModelAttribute ReviewDto reviewDto, HttpSession session) {
//        UserEntity user = (UserEntity) session.getAttribute("loginUser");
////        log.info("user: {}", user);
//
//        Long userId = user.getId(); // 페이지 연결하고 풀기
//        // 로그인하고 페이지 들어갔는데 userId가 없다고 뜸
////        Long userId = 1L; // 테스트용
//
//        reviewService.updateReview(reviewDto, userId );
//
////        reviewService.updateReview(reviewDto, userId);
//        return "redirect:/reviews/new/" + reviewDto.getMovieId(); // 영화 상세페이지로 이동
//    }

@ResponseBody
@PostMapping("/update")
public Map<String, Object> updateReview(@RequestBody ReviewRequest reviewRequest, HttpSession session) {
//    UserEntity user = (UserEntity) session.getAttribute("loginUser");
    Long UserId = 1L;
//    Long UserId = user.getId();

    log.info("reviewRequest: {}", reviewRequest);
    ////2025-03-12T10:23:12.430+09:00  INFO 9623 --- [miki7] [nio-8080-exec-1] c.e.m.r.controller.ReviewController      : reviewRequest: ReviewRequest(id=3, reviewTitle=1, reviewContent=1, reviewRating=1, reviewImage=null, userId=null, movieId=null, castId=null)

    Map<String, Object> response = new HashMap<>();

    try {
        reviewService.updateReview(reviewRequest,UserId);
        response.put("success", true);
    } catch (Exception e) {
        response.put("success", false);
        response.put("error", e.getMessage());
    }

    return response;
}







    //d 회원은 영화에 대한 리뷰를 삭제할 수 있습니다. (진짜 삭제는 아니고 delete_at 에 날짜만 하면 됨)

}
