package com.sparta.hh99_spring_lv5.model.dto;

import com.sparta.hh99_spring_lv5.model.entity.Product;
import com.sparta.hh99_spring_lv5.model.enumtype.CategoryEnum;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private int price;
    private int amount;
    private String intro;
    private CategoryEnum category;

    public ProductResponseDto(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.amount = product.getAmount();
        this.intro = product.getIntro();
        this.category = product.getCategory();
    }

    public ProductResponseDto(String message) {
        this.productName = message;
    }
}