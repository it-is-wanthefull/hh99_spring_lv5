package com.sparta.hh99_spring_lv5.model.dto;

import com.sparta.hh99_spring_lv5.model.entity.Like;
import lombok.Getter;

@Getter
public class LikeDto {
    private Long likeId;
    private Long userId;
    private int count;

    public LikeDto(Like like) {
        this.likeId = like.getLikeId();
        this.userId = like.getUser().getUserId();
        this.count = like.getCount();
    }
}
