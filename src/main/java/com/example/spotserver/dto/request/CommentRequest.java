package com.example.spotserver.dto.request;

import com.example.spotserver.domain.Comment;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CommentRequest {

    @NotEmpty(message = "댓글 내용을 입력해주세요.")
    private String content;


    public static Comment toEntity(CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        return comment;
    }
}
