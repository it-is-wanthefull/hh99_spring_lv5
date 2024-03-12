package com.sparta.hh99_spring_lv5.model.dto;

import com.sparta.hh99_spring_lv5.model.entity.Like;
import lombok.Getter;

@Getter
public class LikeResponseDto {
    private Long likeId;
    private Long lectureId;
    private Long userId;
    private int count;

    public LikeResponseDto(Like like) {
        this.likeId = like.getLikeId();
        this.lectureId = like.getLecture().getLectureId();
        this.userId = like.getUser().getUserId();
        this.count = like.getCount();
    }

    public LikeResponseDto(int errorMessage) {
        this.count = errorMessage;
    }
}
