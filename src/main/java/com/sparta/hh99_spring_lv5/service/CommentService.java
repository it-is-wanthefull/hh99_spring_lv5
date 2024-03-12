package com.sparta.hh99_spring_lv5.service;

import com.sparta.hh99_spring_lv5.model.dto.CommentRequestDto;
import com.sparta.hh99_spring_lv5.model.dto.CommentResponseDto;
import com.sparta.hh99_spring_lv5.model.entity.Comment;
import com.sparta.hh99_spring_lv5.model.entity.Lecture;
import com.sparta.hh99_spring_lv5.model.entity.User;
import com.sparta.hh99_spring_lv5.repository.CommentRepository;
import com.sparta.hh99_spring_lv5.repository.LectureRepository;
import com.sparta.hh99_spring_lv5.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final LectureRepository lectureRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        // 현재 인증된 사용자 정보 가져오기
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        // 사용자 정보와 DTO의 사용자 정보 일치 여부 확인
        if (!user.getUserId().equals(commentRequestDto.getUserId())) {
            throw new SecurityException("사용자 정보가 일치하지 않습니다.");
        }

        // 강의 정보 가져오기
        Long lectureId = commentRequestDto.getLectureId();
        Lecture lecture = lectureRepository.findById(lectureId)
                                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 강의를 찾을 수 없습니다."));

        // 댓글 생성
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setComment(commentRequestDto.getComment());

        // 댓글 저장
        Comment savedComment = commentRepository.save(comment);

        // 댓글을 강의에 추가
        comment.setLecture(lecture);
        lecture.getCommentMap().put(savedComment.getCommentId(), savedComment);

        // CommentResponseDto 생성
        return new CommentResponseDto(savedComment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        // 현재 인증된 사용자 정보 가져오기
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        // 사용자 정보와 DTO의 사용자 정보 일치 여부 확인
        if (!user.getUserId().equals(commentRequestDto.getUserId())) {
            throw new SecurityException("사용자 정보가 일치하지 않습니다.");
        }

        // 강의 정보 가져오기
        Long lectureId = commentRequestDto.getLectureId();
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 강의를 찾을 수 없습니다."));

        // 댓글 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 댓글을 찾을 수 없습니다."));

        // 댓글 수정
        comment.setComment(commentRequestDto.getComment());

        // 댓글 저장
        Comment updatedComment = commentRepository.save(comment);

        // 댓글을 강의에 추가 및 갱신
        lecture.getCommentMap().put(updatedComment.getCommentId(), updatedComment);
        lectureRepository.save(lecture);

        // CommentResponseDto 생성
        return new CommentResponseDto(updatedComment);
    }

    @Transactional
    public void deleteComment(Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        // 현재 인증된 사용자 정보 가져오기
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        // 사용자 정보와 DTO의 사용자 정보 일치 여부 확인
        if (!user.getUserId().equals(commentRequestDto.getUserId())) {
            throw new SecurityException("사용자 정보가 일치하지 않습니다.");
        }

        // 강의 정보 가져오기
        Long lectureId = commentRequestDto.getLectureId();
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 강의를 찾을 수 없습니다."));

        // 댓글 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 댓글을 찾을 수 없습니다."));

        // 댓글이 속한 강의의 commentList에서 댓글 삭제
        lecture.getCommentMap().remove(commentId);

        // 댓글 삭제
        commentRepository.delete(comment);
    }
}
