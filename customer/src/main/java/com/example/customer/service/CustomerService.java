package com.example.customer.service;


import com.example.customer.dto.CustomerRequest;
import com.example.customer.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse saveCustomer(CustomerRequest request);

    CustomerResponse updateCustomer(Long customerId,
                                    CustomerRequest request);

    String deleteCustomer(Long customerId);

    CustomerResponse getCustomerById(Long customerId);

    List<CustomerResponse> getAllCustomers();

    List<CustomerResponse> searchCustomer(String customerName);

}