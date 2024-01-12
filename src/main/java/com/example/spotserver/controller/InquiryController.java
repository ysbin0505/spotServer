package com.example.spotserver.controller;

import com.example.spotserver.domain.ApiResponse;
import com.example.spotserver.domain.ImageFile;
import com.example.spotserver.domain.ImageStore;
import com.example.spotserver.domain.Inquiry;
import com.example.spotserver.service.ImageFileService;
import com.example.spotserver.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/inquirys")
public class InquiryController {

    private InquiryService inquiryService;
    private ImageFileService imageFileService;
    private ImageStore imageStore;

    @Autowired
    public InquiryController(InquiryService inquiryService, ImageFileService imageFileService, ImageStore imageStore) {
        this.inquiryService = inquiryService;
        this.imageFileService = imageFileService;
        this.imageStore = imageStore;
    }

    @PostMapping
    public ApiResponse addInquiry(@RequestPart Inquiry inquiry, @RequestPart(required = false) List<MultipartFile> files) throws IOException {

        if(files != null) {
            List<ImageFile> imgFiles = imageStore.storeInquiryImages(files);
            imageFileService.saveInquiryImageList(imgFiles);
            inquiry.setImages(imgFiles);
        }

        inquiryService.addInquiry(inquiry);
        ApiResponse response = new ApiResponse<Inquiry>();
        response.setStatus("성공");
        response.setMessage("축하");
        response.setData(inquiry);
        return response;
    }

    @GetMapping
    public ApiResponse getInquirys() {
        List<Inquiry> inquirys = inquiryService.getInquirys();
        ApiResponse response = new ApiResponse();
        response.setStatus("성공");
        response.setMessage("축하");
        response.setData(inquirys);
        return response;
    }

    @GetMapping("/{inquiry_id}")
    public ApiResponse getInquiry(@PathVariable Long inquiry_id) {
        Inquiry inquiry = inquiryService.getInquiry(inquiry_id);
        List<Inquiry> listInquiry = Arrays.asList(inquiry);
        ApiResponse response = new ApiResponse();
        response.setStatus(ApiResponse.SUCCESS_STATUS);
        response.setData(listInquiry);
        response.setMessage("성공했다.");
        return response;
    }

}
