package com.sparta.hh99_spring_lv5.model.dto;

import com.sparta.hh99_spring_lv5.model.entity.Lecture;
import com.sparta.hh99_spring_lv5.model.entity.Like;
import com.sparta.hh99_spring_lv5.model.enumtype.CategoryEnum;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class LectureCategoryResponseDto {
    private Long lectureId;
    private String lectureName;
    private int price;
    private String introduce;
    private CategoryEnum category;
    private LocalDateTime createdAt;
    private Map<Long, CommentDto> commentMap;
    private int totalLikes; // 총 좋아요 수

    public LectureCategoryResponseDto(Lecture lecture) {
        this.lectureId = lecture.getLectureId();
        this.lectureName = lecture.getLectureName();
        this.price = lecture.getPrice();
        this.introduce = lecture.getIntroduce();
        this.category = lecture.getCategory();
        this.createdAt = lecture.getCreatedAt();
        this.commentMap = lecture.getCommentMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> new CommentDto(entry.getValue())));
        this.totalLikes = calculateTotalLikes(lecture);
    }

    public LectureCategoryResponseDto(String errorMessage) {
        this.lectureName = errorMessage;
    }

    private int calculateTotalLikes(Lecture lecture) {
        return lecture.getLikeMap().values().stream()
                .mapToInt(Like::getCount)
                .sum();
    }
}
