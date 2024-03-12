package com.sparta.hh99_spring_lv5.model.dto;

import com.sparta.hh99_spring_lv5.model.entity.Teacher;
import lombok.Getter;

@Getter
public class TeacherDto {
    private Long teacherId;
    private String teacherName;
    private String career;
    private String company;
    private String introduce;

    public TeacherDto(Teacher teacher) {
        this.teacherId = teacher.getTeacherId();
        this.teacherName = teacher.getTeacherName();
        this.career = teacher.getCareer();
        this.company = teacher.getCompany();
        this.introduce = teacher.getIntroduce();
    }
}
