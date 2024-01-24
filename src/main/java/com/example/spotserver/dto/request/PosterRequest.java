package com.example.spotserver.dto.request;


import com.example.spotserver.domain.Poster;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PosterRequest {

    @NotEmpty(message = "제목을 작성해주세요.")
    private String title;

    @NotEmpty(message = "내용을 작성해주세요.")
    private String content;

    public static Poster toEntity(PosterRequest posterRequest) {
        Poster poster = new Poster();
        poster.setTitle(posterRequest.getTitle());
        poster.setContent(posterRequest.getContent());
        return poster;
    }
}
