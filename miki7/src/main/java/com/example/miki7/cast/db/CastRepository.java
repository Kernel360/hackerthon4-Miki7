package com.example.miki7.cast.db;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CastRepository extends JpaRepository<CastEntity,Long> {
    List<CastEntity> findByCastNameContaining(String castName);

    List<CastEntity> findByMovieId(Long movieId); // 영화 ID로 배역 조회

    // ✅ 추가: 배역 + 배우 정보까지 함께 조회
    @Query("SELECT c FROM CastEntity c JOIN FETCH c.actor WHERE c.movie.id = :movieId")
    List<CastEntity> findByMovieWithActors(@Param("movieId") Long movieId);
}
