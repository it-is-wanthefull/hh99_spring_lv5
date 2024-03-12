package com.sparta.hh99_spring_lv5.model.entity;

import com.sparta.hh99_spring_lv5.model.dto.TeacherRegisterRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column(nullable = false)
    private String teacherName;

    @Column(nullable = false)
    private String career;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String introduce;

//    @OneToMany(mappedBy = "teacher")
//    private List<Lecture> lectureList = new ArrayList<>();

    public Teacher(TeacherRegisterRequestDto teacherRegisterRequestDto) {
        this.teacherName = teacherRegisterRequestDto.getTeacherName();
        this.career = teacherRegisterRequestDto.getCareer();
        this.company = teacherRegisterRequestDto.getCompany();
        this.phoneNum = teacherRegisterRequestDto.getPhoneNum();
        this.introduce = teacherRegisterRequestDto.getIntroduce();
    }
}