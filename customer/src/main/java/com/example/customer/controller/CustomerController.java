package com.example.customer.controller;


import com.example.customer.dto.CustomerRequest;
import com.example.customer.dto.CustomerResponse;
import com.example.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public CustomerResponse saveCustomer(
            @Valid @RequestBody CustomerRequest request) {

        return customerService.saveCustomer(request);
    }

    @PutMapping("/update/{customerId}")
    public CustomerResponse updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerRequest request) {

        return customerService.updateCustomer(customerId, request);
    }

    @DeleteMapping("/delete/{customerId}")
    public String deleteCustomer(
            @PathVariable Long customerId) {

        return customerService.deleteCustomer(customerId);
    }

    @GetMapping("/{customerId}")
    public CustomerResponse getCustomerById(
            @PathVariable Long customerId) {

        return customerService.getCustomerById(customerId);
    }

    @GetMapping("/all")
    public List<CustomerResponse> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("/search/{customerName}")
    public List<CustomerResponse> searchCustomer(
            @PathVariable String customerName) {

        return customerService.searchCustomer(customerName);
    }
}
