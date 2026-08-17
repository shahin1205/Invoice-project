package com.example.invoice.controller;


import com.example.invoice.dto.InvoiceRequest;
import com.example.invoice.dto.InvoiceResponse;
import com.example.invoice.pdf.InvoicePdfService;
import com.example.invoice.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoicePdfService invoicePdfService;
    public InvoiceController(InvoiceService invoiceService, InvoicePdfService invoicePdfService) {
        this.invoiceService = invoiceService;
        this.invoicePdfService=invoicePdfService;
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

    @GetMapping("/download/{invoiceNumber}")
    public ResponseEntity<byte[]> downloadInvoice(
            @PathVariable String invoiceNumber) {

        byte[] pdf = invoicePdfService.generateInvoicePdf(invoiceNumber);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + invoiceNumber + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
