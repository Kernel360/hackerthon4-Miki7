package com.example.miki7.review.model;

import com.example.miki7.review.db.ReviewEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String reviewTitle;
    private String reviewContent;
    private Integer reviewRating;
    private String reviewImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String status;
    private Long userId;
    private Long movieId;
    private Long castId;
}
