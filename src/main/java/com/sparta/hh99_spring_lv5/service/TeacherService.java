package com.sparta.hh99_spring_lv5.service;

import com.sparta.hh99_spring_lv5.model.dto.TeacherRegisterRequestDto;
import com.sparta.hh99_spring_lv5.model.dto.TeacherRegisterResponseDto;
import com.sparta.hh99_spring_lv5.model.entity.Teacher;
import com.sparta.hh99_spring_lv5.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherRegisterResponseDto registerTeacher(TeacherRegisterRequestDto teacherRegisterRequestDto) {
        Teacher teacher = new Teacher(teacherRegisterRequestDto);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return new TeacherRegisterResponseDto(savedTeacher);
    }
}
