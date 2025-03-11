package com.example.miki7.actor.service;

import com.example.miki7.actor.db.ActorEntity;
import com.example.miki7.actor.db.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public List<ActorEntity> findActorsByKeyword(String keyword) {

        return actorRepository.findByActorNameContaining(keyword);
    }
}
