package com.example.invoice.controller;


import com.example.invoice.dto.InvoiceRequest;
import com.example.invoice.dto.InvoiceResponse;
import com.example.invoice.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/save")
    public InvoiceResponse saveInvoice(
            @Valid @RequestBody InvoiceRequest request) {

        return invoiceService.saveInvoice(request);
    }

    @PutMapping("/update/{id}")
    public InvoiceResponse updateInvoice(
            @PathVariable Long id,
            @Valid @RequestBody InvoiceRequest request) {

        return invoiceService.updateInvoice(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable Long id) {

        invoiceService.deleteInvoice(id);

        return "Invoice Deleted Successfully";
    }

    @GetMapping("/{id}")
    public InvoiceResponse getInvoiceById(@PathVariable Long id) {

        return invoiceService.getInvoiceById(id);
    }

    @GetMapping("/all")
    public List<InvoiceResponse> getAllInvoices() {

        return invoiceService.getAllInvoices();
    }

    @GetMapping("/search/{invoiceNumber}")
    public List<InvoiceResponse> searchInvoice(
            @PathVariable String invoiceNumber) {

        return invoiceService.searchInvoice(invoiceNumber);
    }
}
