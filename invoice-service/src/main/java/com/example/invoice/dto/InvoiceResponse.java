package com.example.invoice.dto;


import com.example.invoice.enums.InvoiceStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class InvoiceResponse {

    private Long id;

    private String invoiceNumber;

    private Long customerId;

    private LocalDate invoiceDate;

    private LocalDate dueDate;

    private Double subTotal;

    private Double tax;

    private Double discount;

    private Double grandTotal;

    private InvoiceStatus status;

    private LocalDateTime createdAt;

    private List<InvoiceItemResponse> items;

    public InvoiceResponse() {
    }

    // Getters and Setters
}
