package com.example.spotserver.dto.response;

import com.example.spotserver.domain.Location;
import lombok.Data;

@Data
public class LocationResponse {

    private Long id;
    private Double latitude;
    private Double longitude;
    private String title;
    private String address;
    private String description;


    public static LocationResponse toDto(Location location) {

        LocationResponse locationResponse = new LocationResponse();

        locationResponse.setId(location.getId());
        locationResponse.setLatitude(location.getLatitude());
        locationResponse.setLongitude(location.getLongitude());
        locationResponse.setTitle(location.getTitle());
        locationResponse.setAddress(location.getAddress());
        locationResponse.setDescription(location.getDescription());

        return locationResponse;
    }
}
