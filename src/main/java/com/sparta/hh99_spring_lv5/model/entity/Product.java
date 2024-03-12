package com.sparta.hh99_spring_lv5.model.entity;

import com.sparta.hh99_spring_lv5.model.dto.ProductRequestDto;
import com.sparta.hh99_spring_lv5.model.enumtype.CategoryEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private String intro;

    @Column(nullable = false)
    private CategoryEnum category;

    public Product(ProductRequestDto productRequestDto) {
        this.productName = productRequestDto.getProductName();
        this.price = productRequestDto.getPrice();
        this.amount = productRequestDto.getAmount();
        this.intro = productRequestDto.getIntro();
        this.category = productRequestDto.getCategory();
    }
}
