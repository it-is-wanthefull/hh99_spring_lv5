package com.sparta.hh99_spring_lv5.repository;

import com.sparta.hh99_spring_lv5.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
