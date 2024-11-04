package com.springboot.security_jwt_v1.services;

import com.springboot.security_jwt_v1.dtos.ProductRequestDto;
import com.springboot.security_jwt_v1.dtos.ProductResponseDto;
import com.springboot.security_jwt_v1.exceptions.ProductCodeExistsException;
import com.springboot.security_jwt_v1.exceptions.ProductNotFoundException;
import com.springboot.security_jwt_v1.mappers.ProductMapper;
import com.springboot.security_jwt_v1.models.Product;
import com.springboot.security_jwt_v1.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toProductResponseDto);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product= findProductById(id);
        return productMapper.toProductResponseDto(product);
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found!"));
    }

    @Override
    @Transactional
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Product product= productMapper.toProduct(productRequestDto);
        product.setCreationDate(LocalDateTime.now(ZoneId.of("America/Bogota")));//Local time for Colombia
        validateUniqueFields(product);
        return productMapper.toProductResponseDto(productRepository.save(product));
    }

    private void validateUniqueFields(Product product) {
        if(productRepository.existsByProductCode(product.getProductCode())){
            throw new ProductCodeExistsException("Product code: "+ product.getProductCode()+" already exists!");
        }
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto, Long id) {
        Product product= productMapper.toProduct(productRequestDto);
        product.setId(id);

        Product recoveredProduct= findProductById(id);

        validateFieldsUpdateConflict(product,recoveredProduct);

        BeanUtils.copyProperties(product,recoveredProduct,"creationDate");
        return productMapper.toProductResponseDto(recoveredProduct);
    }

    private void validateFieldsUpdateConflict(Product product,Product recoveredProduct) {
        if(productCodeExistsAndBelongsToAnotherInstance(product.getProductCode(),recoveredProduct)){
            throw new ProductCodeExistsException("Product code: "+ product.getProductCode()+" already exists!");
        }
    }

    private boolean productCodeExistsAndBelongsToAnotherInstance(String productCode,Product recoveredProduct) {
        return productRepository.existsByProductCode(productCode) && !productCode.equals(recoveredProduct.getProductCode());
    }

    @Override
    @Transactional
    public void removeProduct(Long id) {
        Product product= findProductById(id);
        productRepository.delete(product);
    }

}
