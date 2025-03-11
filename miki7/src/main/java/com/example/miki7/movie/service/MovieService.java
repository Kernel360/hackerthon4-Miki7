package com.example.miki7.movie.service;

import com.example.miki7.movie.db.MovieEntity;
import com.example.miki7.movie.model.DetailSearchDto;
import com.example.miki7.movie.model.MovieDto;
import com.example.miki7.movie.db.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public List<MovieEntity> getAllMovies() {

        return movieRepository.findAll();
    }

    // 영화 상세 정보 조회
    public MovieDto getMovieId(Long movieId) {
        MovieEntity movieEntity = movieRepository.findById(movieId).orElseThrow();
        return MovieDto.toEntity(movieEntity);
    }

    //영화 제목으로 조회
    public List<MovieEntity> findMoviesByKeyword(String keyword){

        return movieRepository.findByMovieNameOrMovieEnNameContaining(keyword);
    }

    public MovieEntity findMovieById(Long id) {

        return movieRepository.findMovieEntityById(id);
    }

    public List<MovieEntity> detailSearchMovies(DetailSearchDto dto) {
        return movieRepository.findByDetailConditions(
                StringUtils.hasText(dto.getActorName()) ? dto.getActorName() : null,
                StringUtils.hasText(dto.getCastName()) ? dto.getCastName() : null,
                StringUtils.hasText(dto.getMovieName()) ? dto.getMovieName() : null,
                StringUtils.hasText(dto.getGenre()) ? dto.getGenre() : null,
                StringUtils.hasText(dto.getYear()) ? dto.getYear() : null
        );
    }
}
