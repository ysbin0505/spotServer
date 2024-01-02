package com.example.spotserver.controller;

import com.example.spotserver.domain.ApiResponse;
import com.example.spotserver.domain.ImageFile;
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
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/inquirys")
public class ImageFileController {

    private ImageFileService imageFileService;
    private ImageStore imageStore;

    @Autowired
    public ImageFileController(ImageFileService imageFileService, ImageStore imageStore) {
        this.imageFileService = imageFileService;
        this.imageStore = imageStore;
    }


    @GetMapping("/{inquiryId}/imagefiles")
    public ApiResponse<ImageFile> getImageFiles(@PathVariable Long inquiryId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);

        List<ImageFile> imageList = imageFileService.getImageList(inquiryId);
        apiResponse.setData(imageList);

        apiResponse.setMessage("요청 처리 완료.");
        return apiResponse;
    }

    @GetMapping("/imagefile/{imagefileName}")
    public ResponseEntity<Resource> getImagefile(@PathVariable String imagefileName) throws IOException {
        UrlResource resource = new UrlResource("file:" + imageStore.getFullPath(imagefileName));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(resource.getFile().toPath()))
                .body(resource);
    }


}
