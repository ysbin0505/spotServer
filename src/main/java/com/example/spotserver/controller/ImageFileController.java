package com.example.spotserver.controller;

import com.example.spotserver.domain.ApiResponse;
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
@RequestMapping("/imagefiles")
public class ImageFileController {

    private ImageFileService imageFileService;
    private ImageStore imageStore;

    @Autowired
    public ImageFileController(ImageFileService imageFileService, ImageStore imageStore) {
        this.imageFileService = imageFileService;
        this.imageStore = imageStore;
    }


    @GetMapping("/posters/{posterId}")
    public ApiResponse<PosterImage> getPosterImageFilesInfo(@PathVariable Long posterId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);

        List<PosterImage> imageList = imageFileService.getPosterImageList(posterId);
        apiResponse.setData(imageList);

        apiResponse.setMessage("요청 처리 완료.");
        return apiResponse;
    }

    @GetMapping("/inquirys/{imagefileName}")
    public ResponseEntity<Resource> getPosterImagefile(@PathVariable String imagefileName) throws IOException {
        UrlResource resource = new UrlResource("file:" + imageStore.getPosterImgFullPath(imagefileName));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(resource.getFile().toPath()))
                .body(resource);
    }

    @GetMapping("/locations/posters/{poster_id}")
    public ApiResponse<PosterImage> getLocationImageFilesInfo(@PathVariable Long poster_id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);

        //List<ImageFile> imageList = imageFileService.getInquiryImageList(poster_id);
        List<PosterImage> imageList = null;
        apiResponse.setData(imageList);

        apiResponse.setMessage("요청 처리 완료.");
        return apiResponse;
    }

    @GetMapping("/locations/{imagefileName}")
    public ResponseEntity<Resource> getLocationImagefile(@PathVariable String imagefileName) throws IOException {
        UrlResource resource = new UrlResource("file:" + imageStore.getLocationImgFullPath(imagefileName));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(resource.getFile().toPath()))
                .body(resource);
    }


}
