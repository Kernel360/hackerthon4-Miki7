package com.example.miki7.movie.db;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cast")
@Getter
@Setter( AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CastEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String castName;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false)
    private ActorEntity actor;
}
