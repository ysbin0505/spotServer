package com.example.spotserver.dto.request;

import com.example.spotserver.domain.Location;
import lombok.Data;

@Data
public class LocationRequest {

    private Double latitude;
    private Double longitude;
    private String title;
    private String address;
    private String description;


    public static Location toEntity(LocationRequest locationRequest) {
        Location location = new Location();

        location.setTitle(locationRequest.getTitle());
        location.setDescription(locationRequest.getDescription());
        location.setAddress(locationRequest.getAddress());
        location.setLongitude(locationRequest.getLongitude());
        location.setLatitude(locationRequest.getLatitude());

        return location;
    }

}
