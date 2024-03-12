package com.sparta.hh99_spring_lv5.repository;

import com.sparta.hh99_spring_lv5.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}