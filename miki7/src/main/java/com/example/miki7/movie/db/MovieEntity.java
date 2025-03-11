package com.example.miki7.movie.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "movie_en_name")
    private String movieEnName;

    @Column(name = "year")
    private String year;

    @Column(name = "genre")
    private String genre;

    @Column(name = "run_time")
    private String runTime;

    @Lob
    @Column(name = "plot", columnDefinition = "TEXT")
    private String plot;

    @Lob
    @Column(name = "poster", columnDefinition = "TEXT")
    private String poster;

    @Column(name = "score")
    private String score;

    @Column(name = "status")
    private String status;
}
