package com.example.miki7.review.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ReviewDto {
    private Long id;
    private String reviewTitle;
    private String reviewContent;
    private Integer reviewRating;
    private String reviewImage;
    private LocalDateTime createdAt;
    private String status;
    private Long userId;
    private Long movieId;
}
