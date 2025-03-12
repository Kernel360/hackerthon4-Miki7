package com.example.miki7.review.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByUser_Id(Long userId);
    List<ReviewEntity> findByMovieId(Long movieId);
    List<ReviewEntity> findByMovieIdAndDeletedAtIsNull(Long movieId);
}
