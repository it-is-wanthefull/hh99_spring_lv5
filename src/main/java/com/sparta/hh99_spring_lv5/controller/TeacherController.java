package com.sparta.hh99_spring_lv5.controller;

import com.sparta.hh99_spring_lv5.model.dto.TeacherRegisterRequestDto;
import com.sparta.hh99_spring_lv5.model.dto.TeacherRegisterResponseDto;
import com.sparta.hh99_spring_lv5.model.enumtype.UserRoleEnum;
import com.sparta.hh99_spring_lv5.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    // 강사등록
    @Secured({UserRoleEnum.Authority.ADMIN})
    @PostMapping
    public ResponseEntity<TeacherRegisterResponseDto> registerTeacher(@RequestBody TeacherRegisterRequestDto teacherRegisterRequestDto) {
        try {
            TeacherRegisterResponseDto teacherRegisterResponseDto = teacherService.registerTeacher(teacherRegisterRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(teacherRegisterResponseDto);
        } catch (Exception e) {
            TeacherRegisterResponseDto teacherRegisterResponseDto = new TeacherRegisterResponseDto("강사등록 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(teacherRegisterResponseDto);
        }
    }
}
