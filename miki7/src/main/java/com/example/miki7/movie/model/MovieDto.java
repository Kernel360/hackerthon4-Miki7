package com.example.miki7.movie.model;

import com.example.miki7.movie.db.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {
    private Long id;
    private String movieName;
    private String movieEnName;
    private String year;
    private String genre;
    private String runTime;
    private String plot;
    private String poster;
    private String score;
    private String status;

    // Entity -> DTO
    public static MovieDto toEntity(MovieEntity movieEntity) {
        return MovieDto.builder()
                .id(movieEntity.getId())
                .movieName(movieEntity.getMovieName())
                .movieEnName(movieEntity.getMovieEnName())
                .year(movieEntity.getYear())
                .genre(movieEntity.getGenre())
                .runTime(movieEntity.getRunTime())
                .plot(movieEntity.getPlot())
                .poster(movieEntity.getPoster())
                .score(movieEntity.getScore())
                .status(movieEntity.getStatus())
                .build();
    }

    // DTO -> Entity
    public MovieEntity toEntity() {
        return MovieEntity.builder()
                .movieName(this.movieName)
                .movieEnName(this.movieEnName)
                .year(this.year)
                .genre(this.genre)
                .runTime(this.runTime)
                .plot(this.plot)
                .poster(this.poster)
                .score(this.score)
                .status(this.status)
                .build();
    }
}

