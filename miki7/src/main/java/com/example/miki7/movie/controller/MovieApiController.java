package com.example.miki7.movie.controller;

import com.example.miki7.movie.db.MovieEntity;
import com.example.miki7.movie.model.MovieDto;
import com.example.miki7.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieApiController {

    private final MovieService movieService;

    // 영화 생성
    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {
        MovieDto createMovie = movieService.createMovie(movieDto);
        return ResponseEntity.ok(createMovie);
    }

    // 장르별 영화 조회
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MovieDto>> getMoviesByGenre(@PathVariable String genre) {
        List<MovieDto> movies = movieService.getMoviesByGenre(genre);
        return ResponseEntity.ok(movies);
    }

    // 영화 전체 조회
    @GetMapping("/all")
    public ResponseEntity<List<MovieEntity>> getAll() {
        List<MovieEntity> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{movieId}")
    public MovieDto getMovieId(@PathVariable Long movieId) {
        return movieService.getMovieId(movieId);
    }
}

