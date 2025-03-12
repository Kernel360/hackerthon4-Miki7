package com.example.miki7.actor.db;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "actor")
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actorName;
}
