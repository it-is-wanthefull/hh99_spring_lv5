package com.sparta.hh99_spring_lv5.model.entity;

import com.sparta.hh99_spring_lv5.model.dto.LectureRegisterRequestDto;
import com.sparta.hh99_spring_lv5.model.enumtype.CategoryEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "lectures")
@EntityListeners(AuditingEntityListener.class)
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Column(nullable = false)
    private String lectureName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CategoryEnum category;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    @OneToMany(mappedBy = "lecture")
    @MapKey(name = "commentId")
    private Map<Long, Comment> commentMap = new HashMap<>();

    @OneToMany(mappedBy = "lecture")
    @MapKey(name = "likeId")
    private Map<Long, Like> likeMap = new HashMap<>();

    public Lecture(LectureRegisterRequestDto lectureRegisterRequestDto) {
        this.lectureName = lectureRegisterRequestDto.getLectureName();
        this.price = lectureRegisterRequestDto.getPrice();
        this.introduce = lectureRegisterRequestDto.getIntroduce();
        this.category = lectureRegisterRequestDto.getCategory();
    }
}