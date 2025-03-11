package com.example.miki7.review.service;

import com.example.miki7.review.db.ReviewEntity;
import com.example.miki7.review.model.ReviewDto;
import com.example.miki7.review.model.ReviewRequest;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

    public ReviewDto toDto(ReviewEntity entity) {
        return ReviewDto.builder()
                .id(entity.getId())
                .reviewTitle(entity.getReviewTitle())
                .reviewContent(entity.getReviewContent())
                .reviewRating(entity.getReviewRating())
                .reviewImage(entity.getReviewImage())
                .createdAt(entity.getCreatedAt())
                .status(entity.getStatus())
                .userId(entity.getUser().getId())
                .movieId(entity.getMovie().getId())
                .build();
    }

    public ReviewEntity toEntity(ReviewRequest request) {
        return ReviewEntity.builder()
                .reviewTitle(request.getReviewTitle())
                .reviewContent(request.getReviewContent())
                .reviewRating(request.getReviewRating())
                .reviewImage(request.getReviewImage())
                .build();
    }
}
