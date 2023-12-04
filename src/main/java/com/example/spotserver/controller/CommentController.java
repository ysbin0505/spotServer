package com.example.spotserver.controller;

import com.example.spotserver.domain.ApiResponse;
import com.example.spotserver.domain.Comment;
import com.example.spotserver.domain.Inquiry;
import com.example.spotserver.service.CommentService;
import com.example.spotserver.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;
    private InquiryService inquiryService;


    @Autowired
    public CommentController(CommentService commentService, InquiryService inquiryService) {
        this.commentService = commentService;
        this.inquiryService = inquiryService;
    }

    @PostMapping("/{inquiry_id}")
    public ApiResponse addComment(@PathVariable Long inquiry_id, @RequestBody Comment comment) {
        Inquiry inquiry = inquiryService.getInquiry(inquiry_id);
        comment.setInquiry(inquiry);
        commentService.addComment(comment);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
        apiResponse.setData(comment);
        apiResponse.setMessage("댓글 작성 완료");
        return apiResponse;
    }

    @GetMapping("/{inquiry_id}")
    public ApiResponse getComments(@PathVariable Long inquiry_id) {
        Inquiry inquiry = inquiryService.getInquiry(inquiry_id);
        List<Comment> commentList = inquiry.getCommentList();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
        apiResponse.setData(commentList);
        apiResponse.setMessage("전체 댓글 조회 완료");
        return apiResponse;
    }



}
