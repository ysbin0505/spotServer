package com.example.spotserver.controller;

import com.example.spotserver.domain.ApiResponse;
import com.example.spotserver.domain.Comment;
import com.example.spotserver.domain.Poster;
import com.example.spotserver.service.CommentService;
import com.example.spotserver.service.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;
    private PosterService posterService;


    @Autowired
    public CommentController(CommentService commentService, PosterService posterService) {
        this.commentService = commentService;
        this.posterService = posterService;
    }

    @PostMapping("/{posterId}")
    public ApiResponse addComment(@PathVariable Long posterId, @RequestBody Comment comment) {
        Poster poster = posterService.getPoster(posterId);
        comment.setPoster(poster);
        commentService.addComment(comment);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
        apiResponse.setData(comment);
        return apiResponse;
    }

    @GetMapping("/{posterId}")
    public ApiResponse getComments(@PathVariable Long posterId) {
        List<Comment> comments = commentService.getComments(posterId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(ApiResponse.SUCCESS_STATUS);
        apiResponse.setData(comments);
        return apiResponse;
    }



}
