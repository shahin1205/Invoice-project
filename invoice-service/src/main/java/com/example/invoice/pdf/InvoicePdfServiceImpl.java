package com.example.invoice.pdf;

import com.example.invoice.client.CustomerClient;
import com.example.invoice.dto.client.CustomerResponse;
import com.example.invoice.entity.Invoice;
import com.example.invoice.exception.InvoiceNotFoundException;
import com.example.invoice.repository.InvoiceRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class InvoicePdfServiceImpl implements InvoicePdfService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerClient customerClient;

    public InvoicePdfServiceImpl(
            InvoiceRepository invoiceRepository,
            CustomerClient customerClient) {

        this.invoiceRepository = invoiceRepository;
        this.customerClient = customerClient;
    }
    @Override
    public byte[] generateInvoicePdf(String invoiceNumber) {

        Invoice invoice = invoiceRepository
                .findByInvoiceNumber(invoiceNumber)
                .orElseThrow(() ->
                        new InvoiceNotFoundException(
                                "Invoice not found : " + invoiceNumber));

        CustomerResponse customer =
                customerClient.getCustomer(invoice.getCustomerId());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {

            Document document = new Document();

            PdfWriter.getInstance(document, outputStream);

            document.open();

            document.add(new Paragraph("INVOICE"));

            document.add(new Paragraph("Invoice Number : " + invoice.getInvoiceNumber()));

            document.add(new Paragraph("Customer Name : " + customer.getCustomerName()));

            document.add(new Paragraph("Invoice Date : " + invoice.getInvoiceDate()));

            document.close();

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
