package com.example.invoice.service;



import com.example.invoice.dto.InvoiceRequest;
import com.example.invoice.dto.InvoiceResponse;

import java.util.List;

public interface InvoiceService {

    InvoiceResponse saveInvoice(InvoiceRequest request);

    InvoiceResponse updateInvoice(Long id, InvoiceRequest request);

    void deleteInvoice(Long id);

    InvoiceResponse getInvoiceById(Long id);

    List<InvoiceResponse> getAllInvoices();

    List<InvoiceResponse> searchInvoice(String invoiceNumber);
}
