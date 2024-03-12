package com.sparta.hh99_spring_lv5.model.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long lectureId;
    private Long userId;
    private String comment;
}
