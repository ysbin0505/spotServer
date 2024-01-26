package com.example.spotserver.controller;

import com.example.spotserver.domain.ApiResponse;
import com.example.spotserver.domain.LocationImage;
import com.example.spotserver.domain.PosterImage;
import com.example.spotserver.domain.ImageStore;
import com.example.spotserver.dto.response.LocationImageResponse;
import com.example.spotserver.dto.response.PosterImageResponse;
import com.example.spotserver.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
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
    public ResponseEntity<List<PosterImageResponse>> getPosterImageFilesInfo(@PathVariable Long posterId) {

        List<PosterImage> posterImageList = imageFileService.getPosterImageList(posterId);

        List<PosterImageResponse> posterImageResponseList = new ArrayList<>();
        for (PosterImage posterImage : posterImageList) {
            posterImageResponseList.add(PosterImageResponse.toDto(posterImage));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(posterImageResponseList);
    }

    @GetMapping("/posters/images/{posterImageId}")
    public ResponseEntity<Resource> getPosterImagefile(@PathVariable Long posterImageId) throws IOException {

        PosterImage posterImage = imageFileService.getPosterImage(posterImageId);
        String imagefileName = posterImage.getStoreFileName();

        UrlResource resource = new UrlResource("file:" + imageStore.getPosterImgFullPath(imagefileName));
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(resource.getFile().toPath()))
                .body(resource);
    }

    @GetMapping("/locations/{locationId}/images")
    public ResponseEntity<List<LocationImageResponse>> getLocationImageFilesInfo(@PathVariable Long locationId) {

        List<LocationImage> locationImageList = imageFileService.getLocationImageList(locationId);

        List<LocationImageResponse> locationImageResponseList = new ArrayList<>();
        for (LocationImage locationImage : locationImageList) {
            locationImageResponseList.add(LocationImageResponse.toDto(locationImage));
        }

        return ResponseEntity
                .ok()
                .body(locationImageResponseList);
    }

    @GetMapping("/locations/images/{locationImageId}")
    public ResponseEntity<Resource> getLocationImagefile(@PathVariable Long locationImageId) throws IOException {

        LocationImage locationImage = imageFileService.getLocationImage(locationImageId);
        String imagefileName = locationImage.getStoreFileName();
        UrlResource resource = new UrlResource("file:" + imageStore.getLocationImgFullPath(imagefileName));
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(resource.getFile().toPath()))
                .body(resource);
    }

    @GetMapping("/locations/image/{locationId}")
    public ResponseEntity<Resource> getLocationImageFileByLocationId(@PathVariable Long locationId) throws IOException {
        List<LocationImage> imageList = imageFileService.getLocationImageList(locationId);

        if (imageList != null && !imageList.isEmpty()) {
            LocationImage selectedImage = imageList.get(0);
            String imageFileName = selectedImage.getStoreFileName();

            Resource resource = new UrlResource("file:" + imageStore.getLocationImgFullPath(imageFileName));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .body(resource);
        } else {
            // 해당 locationId에 대한 이미지가 없을 경우 404 에러 반환
            return ResponseEntity.notFound().build();
        }
    }

}
