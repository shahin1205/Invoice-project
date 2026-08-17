package com.example.invoice.dto;

import lombok.Data;

@Data
public class CustomerResponse {

    private Long customerId;

    private String customerName;

    private String companyName;

    private String email;

    private String phoneNumber;

    private String address;

    private String gstNumber;

    // getters setters
}