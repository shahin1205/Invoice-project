package com.example.invoice.pdf;

public interface InvoicePdfService {

    byte[] generateInvoicePdf(String invoiceNumber);

}
