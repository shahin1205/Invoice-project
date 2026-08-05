package com.example.invoice.dto.client;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;

    private String itemName;

    private Double price;

    private Integer quantity;

    public ProductResponse() {
    }

    // getters setters
}
