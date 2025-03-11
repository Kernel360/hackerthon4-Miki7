package com.example.miki7.movie.db;

import com.example.miki7.review.db.ReviewEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieName;
    private String movieEnName;
    private String year;
    private String genre;
    private String runTime;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String plot;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String poster;

    private String score;
    private String status;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<CastEntity> castList;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviews;
}
