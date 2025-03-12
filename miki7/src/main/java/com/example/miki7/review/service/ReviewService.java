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
import jakarta.transaction.Transactional;
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
@Transactional
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


    public List<ReviewEntity> findAllByUserId(Long id) {
        return reviewRepository.findAllByUser_Id(id);
    }

    // 특정 영화의 리뷰 목록 조회
    public List<ReviewDto> getReviewsByMovieId(Long movieId) {
        List<ReviewEntity> reviews = reviewRepository.findByMovieId(movieId);
        return reviews.stream()
                .map(ReviewConverter::toDto) // ✅ Entity → DTO 변환
                .collect(Collectors.toList());
    }


//    public void updateReview(ReviewDto reviewDto, Long userId) {
//        // ✅ 리뷰 조회 (해당 리뷰가 존재하는지 확인)
//        ReviewEntity review = reviewRepository.findById(reviewDto.getId())
//                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 리뷰입니다."));
//
//        // ✅ 작성자 확인 (자신이 작성한 리뷰만 수정 가능)
//        if (!review.getUser().getId().equals(userId)) {
//            throw new SecurityException("본인이 작성한 리뷰만 수정할 수 있습니다.");
//        }
//
//        // ✅ 리뷰 내용 업데이트 (빌더 패턴 사용)
//        // ✅ 리뷰 내용 업데이트 (기존 엔티티 유지)
//        ReviewEntity updatedReview = reviewConverter.toEntity(reviewDto, review.getUser(), review.getMovie(), review.getCast());
//
//
//        reviewRepository.save(updatedReview); // ✅ 업데이트된 리뷰 저장
//    }

    public void updateReview(ReviewRequest reviewRequest, Long userId) {
        // ✅ 리뷰 조회 (해당 리뷰가 존재하는지 확인)
        ReviewEntity review = reviewRepository.findById(reviewRequest.getId())  // ✅ movieId가 아니라 reviewId 사용해야 함
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 리뷰입니다."));

//        // ✅ 작성자 확인 (자신이 작성한 리뷰만 수정 가능)
//        if (!review.getUser().getId().equals(userId)) {
//            throw new SecurityException("본인이 작성한 리뷰만 수정할 수 있습니다.");
//        }

        // 기존 값을 유지하면서 새로운 값만 변경
        ReviewEntity updatedReview = ReviewEntity.builder()
                .id(review.getId()) // 기존 ID 유지
                .reviewTitle(reviewRequest.getReviewTitle() != null ? reviewRequest.getReviewTitle() : review.getReviewTitle())
                .reviewContent(reviewRequest.getReviewContent() != null ? reviewRequest.getReviewContent() : review.getReviewContent())
                .reviewRating(reviewRequest.getReviewRating() != null ? reviewRequest.getReviewRating() : review.getReviewRating())
                .reviewImage(reviewRequest.getReviewImage() != null ? reviewRequest.getReviewImage() : review.getReviewImage())
                .updatedAt(LocalDateTime.now()) // 수정 시간 갱신
                .status(review.getStatus())
                .user(review.getUser())
                .movie(review.getMovie())
                .cast(review.getCast())
                .build();

        reviewRepository.save(updatedReview); // ✅ 변경된 값만 저장
    }
}
