package com.sparta.hh99_spring_lv5.controller;

import com.sparta.hh99_spring_lv5.model.dto.LectureCategoryResponseDto;
import com.sparta.hh99_spring_lv5.model.dto.LectureIdResponseDto;
import com.sparta.hh99_spring_lv5.model.dto.LectureRegisterRequestDto;
import com.sparta.hh99_spring_lv5.model.dto.LectureRegisterResponseDto;
import com.sparta.hh99_spring_lv5.model.enumtype.CategoryEnum;
import com.sparta.hh99_spring_lv5.model.enumtype.UserRoleEnum;
import com.sparta.hh99_spring_lv5.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;

    // 강의등록
    @Secured({UserRoleEnum.Authority.ADMIN})
    @PostMapping
    public ResponseEntity<LectureRegisterResponseDto> registerLecture(@RequestBody LectureRegisterRequestDto lectureRegisterRequestDto) {
        try {
            LectureRegisterResponseDto lectureRegisterResponseDto = lectureService.registerLecture(lectureRegisterRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(lectureRegisterResponseDto);
        } catch (Exception e) {
            LectureRegisterResponseDto lectureRegisterResponseDto = new LectureRegisterResponseDto("강의등록 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(lectureRegisterResponseDto);
        }
    }

    // 강의조회 by강의번호
    @GetMapping("/get/lectureId/{lectureId}")
    public ResponseEntity<LectureIdResponseDto> getLectureByLectureId(@PathVariable Long lectureId) {
        try {
            LectureIdResponseDto lectureIdResponseDto = lectureService.getLectureByLectureId(lectureId);
            return ResponseEntity.status(HttpStatus.OK).body(lectureIdResponseDto);
        } catch (Exception e) {
            LectureIdResponseDto lectureIdResponseDto = new LectureIdResponseDto("강의번호로 강의조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(lectureIdResponseDto);
        }
    }

    // 강의조회 by카테고리
    @GetMapping("/get/category/{category}/order/{order}/direction/{direction}") // 정렬조건은 URL방식으로 해야 사용자가 URL자체를 저장하여 나중에 다시 볼때도 편하다
    public ResponseEntity<List<LectureCategoryResponseDto>> getLectureByCategory(@PathVariable CategoryEnum category, @PathVariable String order, @PathVariable String direction) {
        try {
            List<LectureCategoryResponseDto> lectureCategoryResponseDtoList = lectureService.getLectureByCategory(category, order, direction);
            return ResponseEntity.status(HttpStatus.OK).body(lectureCategoryResponseDtoList);
        } catch (Exception e) {
            List<LectureCategoryResponseDto> lectureCategoryResponseDtoList = Collections.singletonList(new LectureCategoryResponseDto("카테고리로 강의조회 실패: " + e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(lectureCategoryResponseDtoList);
        }
    }
}
