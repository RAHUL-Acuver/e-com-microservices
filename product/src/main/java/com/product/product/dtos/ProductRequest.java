package com.product.product.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Data
public class ProductRequest {

    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private Integer quantity;
    private String imageUrl;

}
