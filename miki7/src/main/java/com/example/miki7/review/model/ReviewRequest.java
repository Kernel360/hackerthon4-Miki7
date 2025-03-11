package com.example.miki7.review.model;

import lombok.Data;

@Data
public class ReviewRequest {
    private String reviewTitle;
    private String reviewContent;
    private Integer reviewRating;
    private String reviewImage;
    private Long userId;
    private Long movieId;
}
