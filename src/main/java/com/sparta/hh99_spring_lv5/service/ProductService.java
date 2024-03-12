package com.sparta.hh99_spring_lv5.service;

import com.sparta.hh99_spring_lv5.model.dto.ProductRequestDto;
import com.sparta.hh99_spring_lv5.model.dto.ProductResponseDto;
import com.sparta.hh99_spring_lv5.model.entity.Product;
import com.sparta.hh99_spring_lv5.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto registerProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(productRequestDto);
        Product savedProduct = productRepository.save(product);
        return new ProductResponseDto(savedProduct);
    }

    @Transactional
    public ProductResponseDto getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.map(ProductResponseDto::new)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
    }

    @Transactional
    public List<ProductResponseDto> getAllProducts(String sortBy, boolean isASC, int pageSize, int page) {
        Sort.Direction direction = isASC ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Product> productList = productRepository.findAll(pageable);
        return productList.getContent().stream().map(ProductResponseDto::new).collect(Collectors.toList());
    }
}