package com.example.spotserver.service;


import com.example.spotserver.domain.Comment;
import com.example.spotserver.domain.Poster;
import com.example.spotserver.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private PosterService posterService;

    @Autowired
    public CommentService(CommentRepository commentRepository, PosterService posterService) {
        this.commentRepository = commentRepository;
        this.posterService = posterService;
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> getComments(Long posterId) {
        Poster poster = posterService.getPoster(posterId);
        return commentRepository.findCommentsByPoster(poster);
    }

    public Comment getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException());
        return comment;
    }
}
