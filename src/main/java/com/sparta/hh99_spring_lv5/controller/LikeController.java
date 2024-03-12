package com.sparta.hh99_spring_lv5.controller;

import com.sparta.hh99_spring_lv5.model.dto.LikeRequestDto;
import com.sparta.hh99_spring_lv5.model.dto.LikeResponseDto;
import com.sparta.hh99_spring_lv5.model.enumtype.UserRoleEnum;
import com.sparta.hh99_spring_lv5.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    // 좋아요 등록/취소
    @Secured({UserRoleEnum.Authority.ADMIN, UserRoleEnum.Authority.USER})
    @PostMapping
    public ResponseEntity<LikeResponseDto> addLike(@RequestBody LikeRequestDto likeRequestDto) {
        try {
            LikeResponseDto likeResponseDto = likeService.addLike(likeRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(likeResponseDto);
        } catch (Exception e) {
            LikeResponseDto likeResponseDto = new LikeResponseDto(-1);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(likeResponseDto);
        }
    }
}
