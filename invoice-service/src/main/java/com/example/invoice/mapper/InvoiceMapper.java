package com.example.invoice.mapper;


import com.example.invoice.dto.*;
import com.example.invoice.entity.Invoice;
import com.example.invoice.entity.InvoiceItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceMapper {

    // Request DTO -> Entity
    public Invoice toEntity(InvoiceRequest request) {

        Invoice invoice = new Invoice();

        invoice.setCustomerId(request.getCustomerId());
        invoice.setInvoiceDate(request.getInvoiceDate());
        invoice.setDueDate(request.getDueDate());

        return invoice;
    }

    // Invoice Item Request -> Entity
    public InvoiceItem toItemEntity(InvoiceItemRequest request) {

        InvoiceItem item = new InvoiceItem();

        item.setProductId(request.getProductId());
        item.setQuantity(request.getQuantity());

        return item;
    }

    // Entity -> Response DTO
    public InvoiceResponse toResponse(Invoice invoice) {

        InvoiceResponse response = new InvoiceResponse();

        response.setId(invoice.getId());
        response.setInvoiceNumber(invoice.getInvoiceNumber());
        response.setCustomerId(invoice.getCustomerId());
        response.setInvoiceDate(invoice.getInvoiceDate());
        response.setDueDate(invoice.getDueDate());
        response.setSubTotal(invoice.getSubTotal());
        response.setTax(invoice.getTax());
        response.setDiscount(invoice.getDiscount());
        response.setGrandTotal(invoice.getGrandTotal());
        response.setStatus(invoice.getStatus());
        response.setCreatedAt(invoice.getCreatedAt());

        List<InvoiceItemResponse> items =
                invoice.getItems()
                        .stream()
                        .map(this::toItemResponse)
                        .collect(Collectors.toList());

        response.setItems(items);

        return response;
    }

    // Item Entity -> Response DTO
    public InvoiceItemResponse toItemResponse(InvoiceItem item) {

        InvoiceItemResponse response = new InvoiceItemResponse();

        response.setProductId(item.getProductId());
        response.setProductName(item.getProductName());
        response.setQuantity(item.getQuantity());
        response.setPrice(item.getPrice());
        response.setTotal(item.getTotal());

        return response;
    }
}
