package com.example.miki7.review.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequest {

    private Long id;
    private String reviewTitle;
    private String reviewContent;
    private Integer reviewRating;
    private String reviewImage;
    private Long userId;
    private Long movieId;
    private Long castId;
}

