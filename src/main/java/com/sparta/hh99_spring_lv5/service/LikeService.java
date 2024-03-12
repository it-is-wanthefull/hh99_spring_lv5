package com.sparta.hh99_spring_lv5.service;

import com.sparta.hh99_spring_lv5.model.dto.LikeRequestDto;
import com.sparta.hh99_spring_lv5.model.dto.LikeResponseDto;
import com.sparta.hh99_spring_lv5.model.entity.Lecture;
import com.sparta.hh99_spring_lv5.model.entity.Like;
import com.sparta.hh99_spring_lv5.model.entity.User;
import com.sparta.hh99_spring_lv5.repository.LectureRepository;
import com.sparta.hh99_spring_lv5.repository.LikeRepository;
import com.sparta.hh99_spring_lv5.repository.UserRepository;
import com.sparta.hh99_spring_lv5.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;

    @Transactional
    public LikeResponseDto addLike(LikeRequestDto likeRequestDto) {
        // 현재 인증된 사용자 정보 가져오기
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        // 사용자 정보와 DTO의 사용자 정보 일치 여부 확인
        if (!user.getUserId().equals(likeRequestDto.getUserId())) {
            throw new SecurityException("사용자 정보가 일치하지 않습니다.");
        }

        // 강의 정보 가져오기
        Long lectureId = likeRequestDto.getLectureId();
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 강의를 찾을 수 없습니다."));

        // Like 엔티티 저장 또는 갱신
        Optional<Like> optionalLike = likeRepository.findByLectureAndUser(lecture, user);
        Like like;
        if (optionalLike.isPresent()) {
            like = optionalLike.get();
            like.setCount(1 - like.getCount());
        } else {
            like = new Like();
            like.setLecture(lecture);
            like.setUser(user);
            like.setCount(1);
        }
        like = likeRepository.save(like);

        // Lecture 엔티티의 likeMap 갱신
        lecture.getLikeMap().put(like.getLikeId(), like);
        lectureRepository.save(lecture);

        return new LikeResponseDto(like);
    }
}
