package com.example.customer.mapper;


import com.example.customer.dto.CustomerRequest;
import com.example.customer.dto.CustomerResponse;
import com.example.customer.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest request) {

        Customer customer = new Customer();

        customer.setCustomerName(request.getCustomerName());
        customer.setCompanyName(request.getCompanyName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setBillingAddress(request.getBillingAddress());
        customer.setShippingAddress(request.getShippingAddress());
        customer.setGstNumber(request.getGstNumber());
        customer.setReceivableAmount(request.getReceivableAmount());
        customer.setStatus(request.getStatus());

        return customer;
    }

    public CustomerResponse toResponse(Customer customer) {

        CustomerResponse response = new CustomerResponse();

        response.setCustomerId(customer.getCustomerId());
        response.setCustomerName(customer.getCustomerName());
        response.setCompanyName(customer.getCompanyName());
        response.setEmail(customer.getEmail());
        response.setPhoneNumber(customer.getPhoneNumber());
        response.setBillingAddress(customer.getBillingAddress());
        response.setShippingAddress(customer.getShippingAddress());
        response.setGstNumber(customer.getGstNumber());
        response.setReceivableAmount(customer.getReceivableAmount());
        response.setStatus(customer.getStatus());
        response.setCreatedAt(customer.getCreatedAt());
        response.setUpdatedAt(customer.getUpdatedAt());

        return response;
    }

}
