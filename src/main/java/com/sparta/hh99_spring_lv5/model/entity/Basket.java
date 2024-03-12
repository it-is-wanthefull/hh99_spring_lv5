package com.sparta.hh99_spring_lv5.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Table(name = "baskets")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketId;

    @Column(nullable = false)
    private Long userId;

    @OneToMany
    @MapKey(name = "productId")
    private Map<Long, Product> productMap = new HashMap<>();
}