package com.sparta.hh99_spring_lv5.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "baskets")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketId;

    @Column(nullable = false)
    private Long userId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Product product;

    @Column(nullable = false)
    private int amount;

    public Basket(Long userId, Product product, int amount) {
        this.userId = userId;
        this.product = product;
        this.amount = amount;
    }
}