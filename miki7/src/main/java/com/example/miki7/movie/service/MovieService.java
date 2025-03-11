package com.example.miki7.movie.service;

import com.example.miki7.movie.db.MovieEntity;
import com.example.miki7.movie.model.MovieDto;
import com.example.miki7.movie.db.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    // 등록
    public MovieDto createMovie(MovieDto movieDto) {
        MovieEntity movieEntity = movieDto.toEntity();
        MovieEntity movie = movieRepository.save(movieEntity);
        return MovieDto.toEntity(movie);
    }

    // 장르별 조회
    public List<MovieDto> getMoviesByGenre(String genre) {
        List<MovieEntity> movies = movieRepository.findMoviesByGenre(genre);
        return movies.stream()
                .map(movie -> MovieDto.builder()
                        .id(movie.getId())
                        .movieName(movie.getMovieName())
                        .movieEnName(movie.getMovieEnName())
                        .year(movie.getYear())
                        .genre(movie.getGenre())
                        .runTime(movie.getRunTime())
                        .plot(movie.getPlot())
                        .poster(movie.getPoster())
                        .score(movie.getScore())
                        .status(movie.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    // 전체 조회
    public List<MovieDto> getAllMovies() {
        List<MovieEntity> movies = movieRepository.findAll();
        List<MovieDto> movieDtos = new ArrayList<>();

        for (MovieEntity movie : movies) {
            MovieDto movieDto = MovieDto.builder()
                    .id(movie.getId())
                    .movieName(movie.getMovieName())
                    .movieEnName(movie.getMovieEnName())
                    .year(movie.getYear())
                    .genre(movie.getGenre())
                    .runTime(movie.getRunTime())
                    .plot(movie.getPlot())
                    .poster(movie.getPoster())
                    .score(movie.getScore())
                    .status(movie.getStatus())
                    .build();
            movieDtos.add(movieDto);
        }

        return movieDtos;
    }

    // 영화 상세 정보 조회
    public MovieDto getMovieId(Long movieId) {
        MovieEntity movieEntity = movieRepository.findById(movieId).orElseThrow();
        return MovieDto.toEntity(movieEntity);
    }

}
