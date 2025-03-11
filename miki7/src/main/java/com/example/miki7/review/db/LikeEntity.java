package com.example.miki7.review.db;

import com.example.miki7.user.db.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review_like")
@Getter
@Setter( AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String likeChoice;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private ReviewEntity review;
}
