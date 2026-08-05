package com.example.invoice.dto.client;

import lombok.Data;

@Data
public class CustomerResponse {

    private Long customerId;

    private String customerName;

    private String email;

    private String gstNumber;

    public CustomerResponse() {
    }

    // getters setters
}
