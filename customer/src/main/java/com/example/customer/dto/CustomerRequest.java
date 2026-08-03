package com.example.customer.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerRequest {

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Enter a valid phone number"
    )
    private String phoneNumber;

    @NotBlank(message = "Billing address is required")
    private String billingAddress;

    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;

    @NotBlank(message = "GST Number is required")
    private String gstNumber;

    @NotNull(message = "Receivable amount is required")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "Receivable amount cannot be negative"
    )
    private BigDecimal receivableAmount;

    @NotBlank(message = "Status is required")
    private String status;

}
