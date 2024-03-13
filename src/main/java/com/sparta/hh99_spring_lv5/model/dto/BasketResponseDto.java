package com.sparta.hh99_spring_lv5.model.dto;

import com.sparta.hh99_spring_lv5.model.entity.Basket;
import com.sparta.hh99_spring_lv5.model.entity.Product;
import lombok.Getter;

@Getter
public class BasketResponseDto {
    private Long basketId;
    private Long userId;
    private Product product;
    private int amount;

    public BasketResponseDto(Basket basket) {
        this.basketId = basket.getBasketId();
        this.userId = basket.getUserId();
        this.product = basket.getProduct();
        this.amount = basket.getAmount();
    }
}
