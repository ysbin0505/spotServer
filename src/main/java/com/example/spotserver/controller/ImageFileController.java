package com.example.spotserver.controller;

import com.example.spotserver.domain.ApiResponse;
import com.example.spotserver.domain.LocationImage;
import com.example.spotserver.domain.PosterImage;
import com.example.spotserver.domain.ImageStore;
import com.example.spotserver.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
public class ImageFileController {

    private ImageFileService imageFileService;
    private ImageStore imageStore;

    @Autowired
    public ImageFileController(ImageFileService imageFileService, ImageStore imageStore) {
        this.imageFileService = imageFileService;
        this.imageStore = imageStore;
    }


    @GetMapping("/posters/{posterId}/images")
    public ApiResponse<PosterImage> getPosterImageFilesInfo(@PathVariable Long posterId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);

        List<PosterImage> posterImageList = imageFileService.getPosterImageList(posterId);
        apiResponse.setData(posterImageList);

        return apiResponse;
    }

    @GetMapping("/posters/images/{posterImageId}")
    public ResponseEntity<Resource> getPosterImagefile(@PathVariable Long posterImageId) throws IOException {


        PosterImage posterImage = imageFileService.getPosterImage(posterImageId);
        String imagefileName = posterImage.getStoreFileName();

        UrlResource resource = new UrlResource("file:" + imageStore.getPosterImgFullPath(imagefileName));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(resource.getFile().toPath()))
                .body(resource);
    }

    @GetMapping("/locations/{locationId}/images")
    public ApiResponse<LocationImage> getLocationImageFilesInfo(@PathVariable Long locationId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);

        List<LocationImage> locationImageList = imageFileService.getLocationImageList(locationId);
        apiResponse.setData(locationImageList);

        return apiResponse;
    }

    @GetMapping("/locations/images/{locationImageId}")
    public ResponseEntity<Resource> getLocationImagefile(@PathVariable Long locationImageId) throws IOException {

        LocationImage locationImage = imageFileService.getLocationImage(locationImageId);
        String imagefileName = locationImage.getStoreFileName();
        UrlResource resource = new UrlResource("file:" + imageStore.getLocationImgFullPath(imagefileName));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(resource.getFile().toPath()))
                .body(resource);
    }


}
