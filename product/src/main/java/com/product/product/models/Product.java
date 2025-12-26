package com.product.product.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private Integer quantity;
    private String imageUrl;
    private Boolean active=true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
