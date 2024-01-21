package com.example.spotserver.controller;

import com.example.spotserver.domain.*;
import com.example.spotserver.service.ImageFileService;
import com.example.spotserver.service.LocationService;
import com.example.spotserver.service.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
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

    @PostMapping("/locations/{locationId}/posters")
    public ApiResponse addPoster(@RequestPart Poster poster,
                                 @RequestPart(required = false) List<MultipartFile> files,
                                 @PathVariable Long locationId,
                                 @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member
                                 ) throws IOException {
        ApiResponse apiResponse = new ApiResponse();

        Location location = locationService.getLocation(locationId);

        if(location == null) {
            apiResponse.setStatus(ApiResponse.FAIL_STATUS);
            apiResponse.setMessage("장소가 존재하지 않음.");
            return apiResponse;
        }


        if(files != null) {
            List<PosterImage> imgFiles = imageStore.storePosterImages(files);
            imageFileService.savePosterImageList(imgFiles);
            for (PosterImage imgFile : imgFiles) {
                imgFile.setPoster(poster);
            }
        }

        poster.setLocation(location);
        poster.setWriter(member);
        posterService.addPoster(poster);
        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
        apiResponse.setMessage("게시글 작성 성공.");
        apiResponse.setData(poster);
        return apiResponse;
    }

    @GetMapping("/locations/{locationId}/posters")
    public ApiResponse getLocationPosters(@PathVariable Long locationId) {

        Location location = locationService.getLocation(locationId);
        List<Poster> posters = posterService.getLocationPosters(location);
        ApiResponse response = new ApiResponse();
        response.setStatus(ApiResponse.SUCCESS_STATUS);
        response.setMessage("전체 게시글 조회 성공.");
        response.setData(posters);
        return response;
    }

    @GetMapping("/posters/{posterId}")
    public ApiResponse getPoster(@PathVariable Long posterId) {
        Poster poster = posterService.getPoster(posterId);
        ApiResponse response = new ApiResponse();
        response.setStatus(ApiResponse.SUCCESS_STATUS);
        response.setData(poster);
        response.setMessage("특정 게시글 조회 성공.");
        return response;
    }

}
