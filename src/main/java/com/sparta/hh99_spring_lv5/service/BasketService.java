package com.sparta.hh99_spring_lv5.service;

import com.sparta.hh99_spring_lv5.model.dto.BasketPriceResponseDto;
import com.sparta.hh99_spring_lv5.model.dto.BasketResponseDto;
import com.sparta.hh99_spring_lv5.model.entity.Basket;
import com.sparta.hh99_spring_lv5.model.entity.Product;
import com.sparta.hh99_spring_lv5.model.entity.User;
import com.sparta.hh99_spring_lv5.repository.BasketRepository;
import com.sparta.hh99_spring_lv5.repository.ProductRepository;
import com.sparta.hh99_spring_lv5.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    @Transactional
    public BasketResponseDto addProductToBasket(Long productId, int amount) {
        // 현재 인증된 사용자 정보 가져오기
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        Long userId = user.getUserId();

        // 상품 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다. ID: " + productId));

        // 상품 수량 확인
        if (product.getAmount() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "상품의 재고가 부족합니다.");
        } else if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수량은 1 이상이어야 합니다.");
        }
        product.setAmount(product.getAmount() - amount);

        // 장바구니에 상품 추가 및 수량 반영
        Basket basket = new Basket(userId, product, amount);
        basketRepository.save(basket);

        // 장바구니 정보 반환
        return new BasketResponseDto(basket);
    }

    @Transactional
    public BasketPriceResponseDto getBasketForCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        Long userId = user.getUserId();

        List<Basket> basketList = basketRepository.findByUserId(userId);

        // 총 결제 예상 금액 계산
        int totalPrice = 0;
        for (Basket basket : basketList) {
            totalPrice += basket.getProduct().getPrice() * basket.getAmount();
        }

        // BasketResponseDto 리스트 생성
        List<BasketResponseDto> basketResponseDtoList = basketList.stream()
                .map(BasketResponseDto::new)
                .collect(Collectors.toList());

        return new BasketPriceResponseDto(basketResponseDtoList, totalPrice);
    }

    @Transactional
    public BasketResponseDto updateBasketItemAmount(Long productId, int amount) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        Long userId = user.getUserId();

        Basket basket = (Basket) basketRepository.findByUserIdAndProduct_ProductId(userId, productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "장바구니에 해당 상품이 없습니다. 상품 ID: " + productId));

        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수량은 1 이상이어야 합니다.");
        }

        basket.setAmount(amount);
        basketRepository.save(basket);

        return new BasketResponseDto(basket);
    }

    @Transactional
    public void deleteItemFromBasket(Long productId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        Long userId = user.getUserId();

        Basket basket = (Basket) basketRepository.findByUserIdAndProduct_ProductId(userId, productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "장바구니에 해당 상품이 없습니다. 상품 ID: " + productId));

        basketRepository.delete(basket);
    }
}