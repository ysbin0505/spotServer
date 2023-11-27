package com.example.spotserver.controller;

import com.example.spotserver.domain.ApiResponse;
import com.example.spotserver.domain.Inquiry;
import com.example.spotserver.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class InquiryController {

    private InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @PostMapping("/inquirys")
    public ApiResponse addInquiry(@RequestBody Inquiry inquiry) {
        inquiryService.addInquiry(inquiry);
        ApiResponse response = new ApiResponse<Inquiry>();
        response.setStatus("성공");
        response.setMessage("축하");
        response.setData(inquiry);
        return response;
    }

    @GetMapping("/inquirys")
    public ApiResponse getInquirys() {
        List<Inquiry> inquirys = inquiryService.getInquirys();
        ApiResponse response = new ApiResponse();
        response.setStatus("성공");
        response.setMessage("축하");
        response.setData(inquirys);
        return response;
    }

    @GetMapping("/inquirys/{id}")
    public ApiResponse getInquiry(@PathVariable Long id) {
        Inquiry inquiry = inquiryService.getInquiry(id);
        List<Inquiry> listInquiry = Arrays.asList(inquiry);
        ApiResponse response = new ApiResponse();
        response.setStatus(ApiResponse.SUCCESS_STATUS);
        response.setData(listInquiry);
        response.setMessage("성공했다.");
        return response;
    }

}
