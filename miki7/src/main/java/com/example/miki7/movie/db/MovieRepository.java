package com.example.miki7.movie.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity,Long> {
}
