package com.sparta.hh99_spring_lv5.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasketPriceResponseDto {
    List<BasketResponseDto> basketResponseDtoList;
    private int totalPrice = 0;

    public BasketPriceResponseDto(List<BasketResponseDto> basketResponseDtoList, int totalPrice) {
        this.basketResponseDtoList = basketResponseDtoList;
        this.totalPrice = totalPrice;
    }
}
