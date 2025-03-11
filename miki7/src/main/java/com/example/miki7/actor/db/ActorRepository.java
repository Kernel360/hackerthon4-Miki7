package com.example.miki7.actor.db;




import com.example.miki7.movie.db.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepository extends JpaRepository<ActorEntity,Long> {


    List<ActorEntity> findByActorNameContaining(String actorName);
}
