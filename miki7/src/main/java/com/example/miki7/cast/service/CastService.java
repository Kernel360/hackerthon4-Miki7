package com.example.miki7.cast.service;

import com.example.miki7.cast.db.CastEntity;
import com.example.miki7.cast.db.CastRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CastService {

    private final CastRepository castRepository;


    public List<CastEntity> findCastsByKeyword(String keyword) {

        return castRepository.findByCastNameContaining(keyword);
    }
}
