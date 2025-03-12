package com.example.miki7.movie.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    @Query("SELECT m FROM MovieEntity m WHERE m.genre LIKE %:genre%")
    List<MovieEntity> findMoviesByGenre(@Param("genre") String genre);


    @Query("SELECT m FROM MovieEntity m WHERE m.movieName LIKE %:keyword% OR m.movieEnName LIKE %:keyword%")
    List<MovieEntity> findByMovieNameOrMovieEnNameContaining(@Param("keyword") String keyword);

    MovieEntity findMovieEntityById(Long id);

    @Query("SELECT DISTINCT m FROM MovieEntity m " +
            "LEFT JOIN m.castList c " +
            "LEFT JOIN c.actor a " +
            "WHERE (:actorName IS NULL OR a.actorName LIKE %:actorName%) " +
            "AND (:castName IS NULL OR c.castName LIKE %:castName%) " +
            "AND (:movieName IS NULL OR m.movieName LIKE %:movieName% OR m.movieEnName LIKE %:movieName%) " +
            "AND (:genre IS NULL OR m.genre LIKE %:genre%) " +
            "AND (:year IS NULL OR m.year = :year)")
    List<MovieEntity> findByDetailConditions(@Param("actorName") String actorName,
                                             @Param("castName") String castName,
                                             @Param("movieName") String movieName,
                                             @Param("genre") String genre,
                                             @Param("year") String year);

    @Modifying
    @Transactional
    @Query("UPDATE MovieEntity m SET m.score = :score WHERE m.id = :id")
    void updateScoreById(@Param("id") Long id, @Param("score") String score);
}
