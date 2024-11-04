package com.springboot.security_jwt_v1.controllers;

import com.springboot.security_jwt_v1.dtos.ProductRequestDto;
import com.springboot.security_jwt_v1.dtos.ProductResponseDto;
import com.springboot.security_jwt_v1.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products")
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @GetMapping(value = "/products-by-id/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping(value = "/products")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody @Valid ProductRequestDto productRequestDto){
        return new ResponseEntity<>(productService.addProduct(productRequestDto),HttpStatus.CREATED);
        //return new ResponseEntity<>("Created new Product",HttpStatus.CREATED);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody @Valid ProductRequestDto productRequestDto,
                                                            @PathVariable Long id){
        return ResponseEntity.ok(productService.updateProduct(productRequestDto,id));
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Object> removeProduct(@PathVariable Long id){
        productService.removeProduct(id);
        return ResponseEntity.ok("product removed successfully!");
    }


}
