package com.sparta.hh99_spring_lv5.controller;

import com.sparta.hh99_spring_lv5.model.dto.BasketPriceResponseDto;
import com.sparta.hh99_spring_lv5.model.dto.BasketResponseDto;
import com.sparta.hh99_spring_lv5.model.entity.Basket;
import com.sparta.hh99_spring_lv5.model.enumtype.UserRoleEnum;
import com.sparta.hh99_spring_lv5.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/baskets")
public class BasketController {
    private final BasketService basketService;

    // 장바구니에 상품 추가
    @Secured({UserRoleEnum.Authority.ADMIN, UserRoleEnum.Authority.USER})
    @PostMapping("/products/{productId}/amount/{amount}")
    public ResponseEntity<BasketResponseDto> addProductToBasket(@PathVariable Long productId, @PathVariable int amount) {
        try {
            BasketResponseDto basketResponseDto = basketService.addProductToBasket(productId, amount);
            return ResponseEntity.status(HttpStatus.OK).body(basketResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 장바구니 조회
    @Secured({UserRoleEnum.Authority.ADMIN, UserRoleEnum.Authority.USER})
    @GetMapping
    public ResponseEntity<BasketPriceResponseDto> getBasketForCurrentUser() {
        try {
            BasketPriceResponseDto basketPriceResponseDtoList = basketService.getBasketForCurrentUser();
            return ResponseEntity.status(HttpStatus.OK).body(basketPriceResponseDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 장바구니 수량 수정 (Patch로 일부 수정)
    @Secured({UserRoleEnum.Authority.ADMIN, UserRoleEnum.Authority.USER})
    @PatchMapping("/products/{productId}/amount/{amount}")
    public ResponseEntity<BasketResponseDto> updateBasketItemAmount(@PathVariable Long productId, @PathVariable int amount) {
        try {
            BasketResponseDto basketResponseDto = basketService.updateBasketItemAmount(productId, amount);
            return ResponseEntity.status(HttpStatus.OK).body(basketResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 장바구니에서 상품 삭제
    @Secured({UserRoleEnum.Authority.ADMIN, UserRoleEnum.Authority.USER})
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteItemFromBasket(@PathVariable Long productId) {
        try {
            basketService.deleteItemFromBasket(productId);
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패...");
        }
    }
}
