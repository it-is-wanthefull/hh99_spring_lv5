package com.sparta.hh99_spring_lv5.controller;

import com.sparta.hh99_spring_lv5.model.dto.CommentRequestDto;
import com.sparta.hh99_spring_lv5.model.dto.CommentResponseDto;
import com.sparta.hh99_spring_lv5.model.enumtype.UserRoleEnum;
import com.sparta.hh99_spring_lv5.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 작성
    @Secured({UserRoleEnum.Authority.ADMIN, UserRoleEnum.Authority.USER})
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto) {
        try {
            CommentResponseDto commentResponseDto = commentService.createComment(commentRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDto);
        } catch (Exception e) {
            CommentResponseDto commentResponseDto = new CommentResponseDto("댓글등록 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commentResponseDto);
        }
    }

    // 댓글 수정
    @Secured({UserRoleEnum.Authority.ADMIN, UserRoleEnum.Authority.USER})
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        try {
            CommentResponseDto commentResponseDto = commentService.updateComment(commentId, commentRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(commentResponseDto);
        } catch (Exception e) {
            CommentResponseDto commentResponseDto = new CommentResponseDto("댓글수정 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commentResponseDto);
        }
    }

    // 댓글 삭제
    @Secured({UserRoleEnum.Authority.ADMIN, UserRoleEnum.Authority.USER})
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        try {
            commentService.deleteComment(commentId, commentRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(new CommentResponseDto("댓글삭제 성공"));
        } catch (Exception e) {
            CommentResponseDto commentResponseDto = new CommentResponseDto("댓글삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commentResponseDto);
        }
    }
}
