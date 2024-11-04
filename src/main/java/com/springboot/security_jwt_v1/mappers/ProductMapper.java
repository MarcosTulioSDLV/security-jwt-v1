package com.springboot.security_jwt_v1.mappers;

import com.springboot.security_jwt_v1.dtos.ProductRequestDto;
import com.springboot.security_jwt_v1.dtos.ProductResponseDto;
import com.springboot.security_jwt_v1.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductResponseDto toProductResponseDto(Product product){
        return modelMapper.map(product,ProductResponseDto.class);
    }

    public Product toProduct(ProductRequestDto productRequestDto){
        return modelMapper.map(productRequestDto,Product.class);
    }

}
