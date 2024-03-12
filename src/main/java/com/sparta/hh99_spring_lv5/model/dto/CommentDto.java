package com.sparta.hh99_spring_lv5.model.dto;

import com.sparta.hh99_spring_lv5.model.entity.Comment;
import lombok.Getter;

@Getter
public class CommentDto {
    private Long commentId;
    private Long userId;
    private String comment;

    public CommentDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.userId = comment.getUser().getUserId();
        this.comment = comment.getComment();
    }
}
