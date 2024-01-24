package com.example.spotserver.dto.response;

import com.example.spotserver.domain.PosterImage;
import lombok.Data;

@Data
public class PosterImageResponse {

    private Long id;
    private String uploadFileName;


    public static PosterImageResponse toDto(PosterImage posterImage) {
        PosterImageResponse posterImageResponse = new PosterImageResponse();
        posterImageResponse.setId(posterImage.getId());
        posterImageResponse.setUploadFileName(posterImage.getUploadFileName());
        return posterImageResponse;
    }
}
