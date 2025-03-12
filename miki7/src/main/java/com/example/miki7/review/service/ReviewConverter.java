package com.example.miki7.review.service;

import com.example.miki7.cast.db.CastEntity;
import com.example.miki7.movie.db.MovieEntity;
import com.example.miki7.review.db.ReviewEntity;
import com.example.miki7.review.model.ReviewDto;
import com.example.miki7.user.db.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReviewConverter {

    // ✅ Entity → DTO 변환 (조회 시 사용)
    public static ReviewDto toDto(ReviewEntity review) {
        return ReviewDto.builder()
                .id(review.getId())
                .reviewTitle(review.getReviewTitle())
                .reviewContent(review.getReviewContent())
                .reviewRating(review.getReviewRating())
                .reviewImage(review.getReviewImage())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .deletedAt(review.getDeletedAt())
                .status(review.getStatus())
                .userId(review.getUser().getId())
                .movieId(review.getMovie().getId())
                .castId(review.getCast() != null ? review.getCast().getId() : null)
                .build();
    }


    // DTO → Entity 변환
    public ReviewEntity toEntity(ReviewDto dto, UserEntity user, MovieEntity movie, CastEntity cast) {
        return ReviewEntity.builder()
                .reviewTitle(dto.getReviewTitle())
                .reviewContent(dto.getReviewContent())
                .reviewRating(dto.getReviewRating())
                .reviewImage(dto.getReviewImage())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status("ACTIVE")
                .user(user)
                .movie(movie)
                .cast(cast)
                .build();
    }


}
