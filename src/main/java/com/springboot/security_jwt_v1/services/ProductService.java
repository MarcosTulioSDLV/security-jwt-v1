package com.springboot.security_jwt_v1.services;

import com.springboot.security_jwt_v1.dtos.ProductRequestDto;
import com.springboot.security_jwt_v1.dtos.ProductResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Page<ProductResponseDto> getAllProducts(Pageable pageable);

    ProductResponseDto getProductById(Long id);

    @Transactional
    ProductResponseDto addProduct(ProductRequestDto productRequestDto);

    @Transactional
    ProductResponseDto updateProduct(ProductRequestDto productRequestDto,Long id);

    @Transactional
    void removeProduct(Long id);


}
