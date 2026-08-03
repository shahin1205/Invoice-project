package com.example.customer.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CustomerResponse {

    private Long customerId;

    private String customerName;

    private String companyName;

    private String email;

    private String phoneNumber;

    private String billingAddress;

    private String shippingAddress;

    private String gstNumber;

    private BigDecimal receivableAmount;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
