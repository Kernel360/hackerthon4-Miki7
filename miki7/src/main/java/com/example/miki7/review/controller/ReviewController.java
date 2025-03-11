package com.example.miki7.review.controller;

import com.example.miki7.review.db.ReviewEntity;
import com.example.miki7.review.model.ReviewRequest;
import com.example.miki7.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import com.example.miki7.actor.db.CastEntity;
import com.example.miki7.user.db.UserEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 리뷰 작성 폼
    @GetMapping("/new/{movieId}")
    public String showReviewForm(@PathVariable Long movieId, Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("loginUser");
        List<CastEntity> castList = reviewService.getCastsByMovieId(movieId); // 해당영화 배역 목록
        model.addAttribute("movieId", movieId); // 선택한 영화
        model.addAttribute("castList", castList); // 영화에 출연한 배우/배역 목록 선택을 위해
        model.addAttribute("review", new ReviewEntity());

//        log.info("castList: {}", castList); // ✅ castList 값 확인

        return "review-form"; // 타임리프 템플릿 (@테스트 완료 바꾸기)
    }

    // 리뷰 저장 요청 처리
    @PostMapping("/save")
    public String saveReview(@ModelAttribute ReviewRequest reviewRequest, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("loginUser");

//        Long userId = user.getId(); // 페이지 연결하고 풀기
        Long userId = 1L; // 페이지 연결하고 풀기


        reviewService.saveReview(reviewRequest,userId);
        return "redirect:/reviews/new/" + reviewRequest.getMovieId(); // @영화 상세페이지 경로로 변경

    }








    //r 회원은 영화에 대한 리뷰를 조회할 수 있습니다.
    //u 회원은 영화에 대한 리뷰를 수정할 수 있습니다.
    //d 회원은 영화에 대한 리뷰를 삭제할 수 있습니다. (진짜 삭제는 아니고 delete_at 에 날짜만 하면 됨)

}
