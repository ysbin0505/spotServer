package com.example.spotserver.dto.response;

import com.example.spotserver.domain.Poster;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PosterResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime regDate;


    public static PosterResponse toDto(Poster poster) {
        PosterResponse posterResponse = new PosterResponse();
        posterResponse.setId(poster.getId());
        posterResponse.setTitle(poster.getTitle());
        posterResponse.setContent(poster.getContent());
        posterResponse.setRegDate(poster.getRegDate());
        return posterResponse;
    }
}
