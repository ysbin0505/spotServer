package com.example.spotserver.dto.response;

import com.example.spotserver.domain.LocationImage;
import com.example.spotserver.domain.PosterImage;
import lombok.Data;

@Data
public class LocationImageResponse {

    private Long id;
    private String uploadFileName;

    public static LocationImageResponse toDto(LocationImage locationImage) {
        LocationImageResponse locationImageResponse = new LocationImageResponse();
        locationImageResponse.setId(locationImage.getId());
        locationImageResponse.setUploadFileName(locationImage.getUploadFileName());
        return locationImageResponse;
    }
}
