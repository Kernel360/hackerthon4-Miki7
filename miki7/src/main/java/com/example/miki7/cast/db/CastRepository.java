package com.example.miki7.cast.db;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CastRepository extends JpaRepository<CastEntity,Long> {
    List<CastEntity> findByCastNameContaining(String castName);

    List<CastEntity> findByMovieId(Long movieId);
}
