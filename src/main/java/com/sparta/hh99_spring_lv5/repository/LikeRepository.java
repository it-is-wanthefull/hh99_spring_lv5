package com.sparta.hh99_spring_lv5.repository;

import com.sparta.hh99_spring_lv5.model.entity.Lecture;
import com.sparta.hh99_spring_lv5.model.entity.Like;
import com.sparta.hh99_spring_lv5.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByLectureAndUser(Lecture lecture, User user);
}
