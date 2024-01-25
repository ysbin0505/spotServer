package com.example.spotserver.dto.request;

import com.example.spotserver.domain.Location;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LocationRequest {

    @NotEmpty(message = "위도를 입력해주세요.")
    private Double latitude;

    @NotEmpty(message = "경도를 입력해주세요.")
    private Double longitude;

    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;

    @NotEmpty(message = "주소를 입력해주세요.")
    private String address;

    @NotEmpty(message = "설명을 입력해주세요.")
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
