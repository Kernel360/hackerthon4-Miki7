package com.example.miki7.actor.db;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CastRepository extends JpaRepository<CastEntity,Long> {
    List<CastEntity> findByMovieId(Long movieId);
}
