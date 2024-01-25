package com.example.spotserver.controller;

import com.example.spotserver.domain.*;
import com.example.spotserver.dto.request.PosterRequest;
import com.example.spotserver.dto.response.PosterResponse;
import com.example.spotserver.service.ImageFileService;
import com.example.spotserver.service.LocationService;
import com.example.spotserver.service.PosterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PosterController {

    private PosterService posterService;
    private LocationService locationService;
    private ImageFileService imageFileService;
    private ImageStore imageStore;

    @Autowired
    public PosterController(PosterService posterService, LocationService locationService, ImageFileService imageFileService, ImageStore imageStore) {
        this.posterService = posterService;
        this.locationService = locationService;
        this.imageFileService = imageFileService;
        this.imageStore = imageStore;
    }

    @PostMapping(value = "/locations/{locationId}/posters", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PosterResponse> addPoster(@Valid @RequestPart PosterRequest posterRequest,
                                                    @RequestPart(required = false) List<MultipartFile> files,
                                                    @PathVariable Long locationId,
                                                    @AuthenticationPrincipal(expression = "member") Member member) throws IOException {
        Location location = locationService.getLocation(locationId);

        if (location == null) {
            return null;
        }


        Poster poster = PosterRequest.toEntity(posterRequest);
        poster.setLocation(location);
        poster.setWriter(member);
        posterService.addPoster(poster);

        if (files != null) {
            List<PosterImage> imgFiles = imageStore.storePosterImages(files);
            for (PosterImage imgFile : imgFiles) {
                imgFile.setPoster(poster);
            }
            imageFileService.savePosterImageList(imgFiles);
        }

        PosterResponse posterResponse = PosterResponse.toDto(poster);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(posterResponse);
    }

    @GetMapping("/locations/{locationId}/posters")
    public ResponseEntity<List<PosterResponse>> getLocationPosters(@PathVariable Long locationId) {

        Location location = locationService.getLocation(locationId);
        List<Poster> posters = posterService.getLocationPosters(location);

        List<PosterResponse> posterResponseList = new ArrayList<>();
        for (Poster poster : posters) {
            posterResponseList.add(PosterResponse.toDto(poster));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(posterResponseList);
    }

    @GetMapping("/posters/{posterId}")
    public ResponseEntity<PosterResponse> getPoster(@PathVariable Long posterId) {
        Poster poster = posterService.getPoster(posterId);
        PosterResponse posterResponse = PosterResponse.toDto(poster);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(posterResponse);
    }

}
