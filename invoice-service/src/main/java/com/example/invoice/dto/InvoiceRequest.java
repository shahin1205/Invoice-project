package com.example.invoice.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class InvoiceRequest {

    @NotNull(message = "Customer Id is required")
    private Long customerId;

    @NotNull(message = "Invoice Date is required")
    private LocalDate invoiceDate;

    @NotNull(message = "Due Date is required")
    private LocalDate dueDate;

    private Double tax;

    private Double discount;

    @Valid
    private List<InvoiceItemRequest> items;

    public InvoiceRequest() {
    }

    // Getters and Setters
}
