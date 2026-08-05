package com.example.invoice.dto;

import lombok.Data;

@Data
public class InvoiceItemResponse {

    private Long productId;

    private String productName;

    private Integer quantity;

    private Double price;

    private Double total;

    public InvoiceItemResponse() {
    }

    // Getters and Setters
}
