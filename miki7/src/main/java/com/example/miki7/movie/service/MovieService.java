package com.example.miki7.movie.service;

import com.example.miki7.movie.db.MovieEntity;
import com.example.miki7.movie.model.MovieDto;
import com.example.miki7.movie.db.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieDto createMovie(MovieDto movieDto) {
        MovieEntity movieEntity = movieDto.toEntity();
        MovieEntity movie = movieRepository.save(movieEntity);
        return MovieDto.toEntity(movie);
    }

}
