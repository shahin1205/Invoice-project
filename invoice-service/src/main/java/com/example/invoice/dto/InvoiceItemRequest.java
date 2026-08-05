package com.example.invoice.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class InvoiceItemRequest {

    @NotNull(message = "Product Id is required")
    private Long productId;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    public InvoiceItemRequest() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
