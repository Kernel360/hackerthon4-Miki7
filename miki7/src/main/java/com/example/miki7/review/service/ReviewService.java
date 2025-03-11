package com.example.miki7.review.service;

import com.example.miki7.cast.db.CastEntity;
import com.example.miki7.cast.db.CastRepository;
import com.example.miki7.movie.db.MovieEntity;
import com.example.miki7.movie.db.MovieRepository;
import com.example.miki7.review.db.ReviewEntity;
import com.example.miki7.review.db.ReviewRepository;
import com.example.miki7.review.model.ReviewDto;
import com.example.miki7.review.model.ReviewRequest;
import com.example.miki7.user.db.UserEntity;
import com.example.miki7.user.db.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CastRepository castRepository;
    private final ReviewConverter reviewConverter;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    // 리뷰 저장
    public void saveReview(ReviewRequest reviewRequest, Long userId) {
         //유저, 영화, 배역 찾기
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        MovieEntity movie = movieRepository.findById(reviewRequest.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 영화입니다."));

        CastEntity cast = Optional.ofNullable(reviewRequest.getCastId())
                .flatMap(castRepository::findById)
                .orElse(null);

        //DTO 요청

        ReviewDto reviewDto = ReviewDto.builder()
                .reviewTitle(reviewRequest.getReviewTitle())
                .reviewContent(reviewRequest.getReviewContent())
                .reviewRating(reviewRequest.getReviewRating())
                .reviewImage(reviewRequest.getReviewImage())
                .userId(userId) // 컨트롤러에서 받은 userId 설정
                .movieId(reviewRequest.getMovieId())
                .castId(reviewRequest.getCastId()) // 선택 사항
                .status("ACTIVE") // 기본 상태 설정
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

//        log.info("user : {}, movie : {}, cast : {}", user.toString(), movie.toString());


        // DTO → Entity 변환 후 저장

        ReviewEntity review = reviewConverter.toEntity(reviewDto, user, movie, cast);
        reviewRepository.save(review);
    }

    public List<CastEntity> getCastsByMovieId(Long movieId) {
        return castRepository.findByMovieWithActors(movieId);
    }










    // 특정 영화의 리뷰 목록 조회
    public List<ReviewDto> getReviewsByMovieId(Long movieId) {
        List<ReviewEntity> reviews = reviewRepository.findByMovieId(movieId);
        return reviews.stream()
                .map(ReviewConverter::toDto) // ✅ Entity → DTO 변환
                .collect(Collectors.toList());
    }




}
