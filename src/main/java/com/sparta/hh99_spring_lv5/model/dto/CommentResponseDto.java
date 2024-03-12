package com.sparta.hh99_spring_lv5.model.dto;

import com.sparta.hh99_spring_lv5.model.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long commentId;
    private Long lectureId;
    private Long userId;
    private String comment;

    public CommentResponseDto(Comment savedComment) {
        this.commentId = savedComment.getCommentId();
        this.lectureId = savedComment.getLecture().getLectureId();
        this.userId = savedComment.getUser().getUserId();
        this.comment = savedComment.getComment();
    }

    public CommentResponseDto(String errorMessage) {
        this.comment = errorMessage;
    }
}
