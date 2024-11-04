package com.springboot.security_jwt_v1.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class ProductResponseDto {

    private Long id;

    private String productCode;

    private String name;

    private BigDecimal price;

    private String country;

    private LocalDateTime creationDate;

}
