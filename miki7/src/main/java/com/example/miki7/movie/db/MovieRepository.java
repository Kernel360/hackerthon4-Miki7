package com.example.miki7.movie.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    @Query("SELECT m FROM MovieEntity m WHERE m.genre LIKE %:genre%")
    List<MovieEntity> findMoviesByGenre(@Param("genre") String genre);
}
