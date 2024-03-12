package com.sparta.hh99_spring_lv5.model.dto;

import com.sparta.hh99_spring_lv5.model.entity.Teacher;
import lombok.Getter;

@Getter
public class TeacherRegisterResponseDto {
    private Long teacherId;
    private String teacherName;
    private String career;
    private String company;
    private String phoneNum;
    private String introduce;

    public TeacherRegisterResponseDto(Teacher teacher) {
        this.teacherId = teacher.getTeacherId();
        this.teacherName = teacher.getTeacherName();
        this.career = teacher.getCareer();
        this.company = teacher.getCompany();
        this.phoneNum = teacher.getPhoneNum();
        this.introduce = teacher.getIntroduce();
    }

    public TeacherRegisterResponseDto(String errorMessage) {
        this.teacherName = errorMessage;
    }
}
