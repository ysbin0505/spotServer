package com.example.spotserver.controller;

import com.example.spotserver.domain.*;
import com.example.spotserver.dto.response.LocationResponse;
import com.example.spotserver.service.ImageFileService;
import com.example.spotserver.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private LocationService locationService;
    private ImageFileService imageFileService;
    private ImageStore imageStore;

    public LocationController(LocationService locationService, ImageFileService imageFileService, ImageStore imageStore) {
        this.locationService = locationService;
        this.imageFileService = imageFileService;
        this.imageStore = imageStore;
    }

    @GetMapping
    public ResponseEntity<List<LocationResponse>> getLocations(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude) {
        List<Location> locations = locationService.getLocations(latitude, longitude);

        List<LocationResponse> locationResponseList = new ArrayList<>();
        for (Location location : locations) {
            locationResponseList.add(LocationResponse.toDto(location));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locationResponseList);
    }

    @PostMapping
    public ResponseEntity<LocationResponse> addLocation(@RequestPart Location location,
                                                        @RequestPart(required = false) List<MultipartFile> files) throws IOException {

        locationService.addLocation(location);

        if (files != null) {
            List<LocationImage> imgFiles = imageStore.storeLocationImages(files);

            for (LocationImage imgFile : imgFiles) {
                imgFile.setLocation(location);
            }

            imageFileService.saveLocationImageList(imgFiles);
        }

        LocationResponse locationResponse = LocationResponse.toDto(location);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locationResponse);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationResponse> getLocation(@PathVariable Long locationId) {

        Location location = locationService.getLocation(locationId);

        LocationResponse locationResponse = LocationResponse.toDto(location);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locationResponse);
    }

}
