package com.example.product.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    @NotBlank(message = "Product code is required")
    private String productCode;

    @NotBlank(message = "Product name is required")
    private String productName;

    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    @DecimalMin(value = "0.0", inclusive = false,
            message = "Price must be greater than zero")
    private BigDecimal price;

    @Min(value = 0,
            message = "Quantity cannot be negative")
    private Integer quantity;

    @NotBlank(message = "Status is required")
    private String status;
}
