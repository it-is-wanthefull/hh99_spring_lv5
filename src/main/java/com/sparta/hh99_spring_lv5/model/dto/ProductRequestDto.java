package com.sparta.hh99_spring_lv5.model.dto;

import com.sparta.hh99_spring_lv5.model.enumtype.CategoryEnum;
import lombok.Getter;

@Getter
public class ProductRequestDto {
    private String productName;
    private int price;
    private int amount;
    private String intro;
    private CategoryEnum category;
}