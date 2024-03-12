package com.sparta.hh99_spring_lv5.controller;

import com.sparta.hh99_spring_lv5.model.dto.ProductRequestDto;
import com.sparta.hh99_spring_lv5.model.dto.ProductResponseDto;
import com.sparta.hh99_spring_lv5.model.enumtype.UserRoleEnum;
import com.sparta.hh99_spring_lv5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    // 상품 등록
    @Secured({UserRoleEnum.Authority.ADMIN})
    @PostMapping
    public ResponseEntity<ProductResponseDto> registerProduct(@RequestBody ProductRequestDto productRequestDto) {
        try {
            ProductResponseDto productResponseDto = productService.registerProduct(productRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
        } catch (Exception e) {
            ProductResponseDto productResponseDto = new ProductResponseDto("상품 등록 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productResponseDto);
        }
    }

    // 상품 단일 조회 by 상품번호
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long productId) {
        try {
            ProductResponseDto productResponseDto = productService.getProductById(productId);
            return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
        } catch (Exception e) {
            ProductResponseDto productResponseDto = new ProductResponseDto("상품 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productResponseDto);
        }
    }

    // 상품 전체 조회
    @GetMapping("/sortBy/{sortBy}/isASC/{isASC}/pageSize/{pageSize}/page/{page}")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(@PathVariable String sortBy, @PathVariable boolean isASC, @PathVariable int pageSize, @PathVariable int page) {
        try {
            List<ProductResponseDto> productResponseDtoList = productService.getAllProducts(sortBy, isASC, pageSize, page);
            return ResponseEntity.status(HttpStatus.OK).body(productResponseDtoList);
        } catch (Exception e) {
            ProductResponseDto productResponseDto = new ProductResponseDto("상품 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}