package com.example.invoice.service;


import com.example.invoice.client.CustomerClient;
import com.example.invoice.client.ProductClient;
import com.example.invoice.dto.InvoiceItemRequest;
import com.example.invoice.dto.InvoiceRequest;
import com.example.invoice.dto.InvoiceResponse;
import com.example.invoice.dto.client.CustomerResponse;
import com.example.invoice.dto.client.ProductResponse;
import com.example.invoice.entity.Invoice;
import com.example.invoice.entity.InvoiceItem;
import com.example.invoice.enums.InvoiceStatus;
import com.example.invoice.exception.CustomerNotFoundException;
import com.example.invoice.exception.InvoiceNotFoundException;
import com.example.invoice.exception.ProductNotFoundException;
import com.example.invoice.mapper.InvoiceMapper;
import com.example.invoice.repository.InvoiceRepository;
import com.example.invoice.repository.InvoiceItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public InvoiceServiceImpl(
            InvoiceRepository invoiceRepository,
            InvoiceMapper invoiceMapper,
            CustomerClient customerClient,
            ProductClient productClient) {

        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerClient = customerClient;
        this.productClient = productClient;
    }
    private String generateInvoiceNumber() {

        long count = invoiceRepository.count() + 1;

        return "INV-" + String.format("%04d", count);
    }

    @Override
    public InvoiceResponse saveInvoice(InvoiceRequest request) {

        // Validate Customer
        CustomerResponse customer =
                customerClient.getCustomer(request.getCustomerId());

        Invoice invoice = invoiceMapper.toEntity(request);

        invoice.setInvoiceNumber(generateInvoiceNumber());
        invoice.setStatus(InvoiceStatus.PENDING);
        invoice.setCreatedAt(LocalDateTime.now());

        List<InvoiceItem> items = new ArrayList<>();

        double subTotal = 0;

        for (InvoiceItemRequest itemRequest : request.getItems()) {

            ProductResponse product =
                    productClient.getProduct(itemRequest.getProductId());

//            if (product == null) {
//                throw new ProductNotFoundException(
//                        "Product not found : "
//                                + itemRequest.getProductId());
//            }

            InvoiceItem item = invoiceMapper.toItemEntity(itemRequest);

            item.setProductName(product.getItemName());

            item.setPrice(product.getPrice());

            double total =
                    product.getPrice() * itemRequest.getQuantity();

            item.setTotal(total);

            item.setInvoice(invoice);

            items.add(item);

            subTotal += total;
        }

        invoice.setItems(items);

        invoice.setSubTotal(subTotal);

        double taxAmount = (subTotal * request.getTax()) / 100;

        invoice.setTax(taxAmount);

        invoice.setDiscount(request.getDiscount());

        invoice.setGrandTotal(
                subTotal
                        + taxAmount
                        - request.getDiscount());

        Invoice saved =
                invoiceRepository.save(invoice);

        return invoiceMapper.toResponse(saved);
    }

    @Override
    public InvoiceResponse getInvoiceById(Long id) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new InvoiceNotFoundException("Invoice not found with id : " + id));

        return invoiceMapper.toResponse(invoice);
    }
    @Override
    public List<InvoiceResponse> getAllInvoices() {

        return invoiceRepository.findAll()
                .stream()
                .map(invoiceMapper::toResponse)
                .toList();
    }
    @Override
    public void deleteInvoice(Long id) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new InvoiceNotFoundException("Invoice not found with id : " + id));

        invoiceRepository.delete(invoice);
    }
    @Override
    public List<InvoiceResponse> searchInvoice(String invoiceNumber) {

        Invoice invoice = invoiceRepository
                .findByInvoiceNumber(invoiceNumber)
                .orElseThrow(() ->
                        new InvoiceNotFoundException("Invoice not found"));

        return List.of(invoiceMapper.toResponse(invoice));
    }
    @Override
    public InvoiceResponse updateInvoice(Long id, InvoiceRequest request) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new InvoiceNotFoundException("Invoice not found"));

        invoice.setCustomerId(request.getCustomerId());
        invoice.setInvoiceDate(request.getInvoiceDate());
        invoice.setDueDate(request.getDueDate());

        invoice.getItems().clear();

        double subtotal = 0;

        List<InvoiceItem> items = new ArrayList<>();

        for (InvoiceItemRequest itemRequest : request.getItems()) {

            ProductResponse product =
                    productClient.getProduct(itemRequest.getProductId());

            InvoiceItem item = invoiceMapper.toItemEntity(itemRequest);

            item.setInvoice(invoice);

            item.setProductName(product.getItemName());

            item.setPrice(product.getPrice());

            double total =
                    product.getPrice() * itemRequest.getQuantity();

            item.setTotal(total);

            subtotal += total;

            items.add(item);
        }

        invoice.setItems(items);

        invoice.setSubTotal(subtotal);

        double tax =
                subtotal * request.getTax() / 100;

        invoice.setTax(tax);

        invoice.setDiscount(request.getDiscount());

        invoice.setGrandTotal(
                subtotal + tax - request.getDiscount());

        invoice.setUpdatedAt(LocalDateTime.now());

        return invoiceMapper.toResponse(
                invoiceRepository.save(invoice));
    }
}
