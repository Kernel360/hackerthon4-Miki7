package com.example.miki7.movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailSearchDto {
    private String actorName;
    private String castName;
    private String movieName;
    private String genre;
    private String year;
}
