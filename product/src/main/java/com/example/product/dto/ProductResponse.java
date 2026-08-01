package com.example.product.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductResponse {

    private Long id;

    private String productCode;

    private String productName;

    private String description;

    private String category;

    private BigDecimal price;

    private Integer quantity;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
