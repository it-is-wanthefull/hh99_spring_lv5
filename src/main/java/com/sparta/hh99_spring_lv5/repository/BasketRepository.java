package com.sparta.hh99_spring_lv5.repository;

import com.sparta.hh99_spring_lv5.model.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findByUserId(Long userId);
    Optional<Basket> findByUserIdAndProduct_ProductId(Long userId, Long productId);
}