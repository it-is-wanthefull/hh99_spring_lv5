package com.sparta.hh99_spring_lv5.service;

import com.sparta.hh99_spring_lv5.model.dto.LectureCategoryResponseDto;
import com.sparta.hh99_spring_lv5.model.dto.LectureIdResponseDto;
import com.sparta.hh99_spring_lv5.model.dto.LectureRegisterRequestDto;
import com.sparta.hh99_spring_lv5.model.dto.LectureRegisterResponseDto;
import com.sparta.hh99_spring_lv5.model.entity.Lecture;
import com.sparta.hh99_spring_lv5.model.entity.Teacher;
import com.sparta.hh99_spring_lv5.model.enumtype.CategoryEnum;
import com.sparta.hh99_spring_lv5.repository.LectureRepository;
import com.sparta.hh99_spring_lv5.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;

    public LectureRegisterResponseDto registerLecture(LectureRegisterRequestDto lectureRegisterRequestDto) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(lectureRegisterRequestDto.getTeacherId());
        Lecture lecture = new Lecture(lectureRegisterRequestDto);
        lecture.setTeacher(optionalTeacher.get());
        Lecture savedLecture = lectureRepository.save(lecture);
        return new LectureRegisterResponseDto(savedLecture);
    }

    public LectureIdResponseDto getLectureByLectureId(Long lectureId) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(lectureId);

        if(optionalLecture.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 강의를 찾을 수 없습니다.");
        } else {
            return new LectureIdResponseDto(optionalLecture.get());
        }
    }

    public List<LectureCategoryResponseDto> getLectureByCategory(CategoryEnum category, String order, String direction) {
        Sort sort;
        if (order == null || direction == null) {
            throw new IllegalArgumentException("order = lectureName/price/createdAt, direction = ASC/DESC 입니다, 정확히 입력해주세요.");
        } else {
            sort = Sort.by(Sort.Direction.fromString(direction), order);
        }

        Optional<List<Lecture>> optionalLectureList = lectureRepository.findAllByCategory(category, sort);

        if (optionalLectureList.isEmpty()) {
            throw new NullPointerException("해당 카테고리로 검색된 결과가 존재하지 않습니다.");
        } else {
            return optionalLectureList.get().stream().map(LectureCategoryResponseDto::new).toList();
        }
    }
}
