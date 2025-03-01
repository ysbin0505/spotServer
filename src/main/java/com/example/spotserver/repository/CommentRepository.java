package com.example.spotserver.repository;

import com.example.spotserver.domain.Comment;
import com.example.spotserver.domain.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPoster(Poster poster);
}
