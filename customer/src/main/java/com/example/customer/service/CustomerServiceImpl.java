package com.example.customer.service;


import com.example.customer.dto.CustomerRequest;
import com.example.customer.dto.CustomerResponse;
import com.example.customer.entity.Customer;
import com.example.customer.exception.CustomerNotFoundException;
import com.example.customer.exception.EmailAlreadyExistsException;
import com.example.customer.exception.GstNumberAlreadyExistsException;
import com.example.customer.exception.PhoneNumberAlreadyExistsException;
import com.example.customer.mapper.CustomerMapper;
import com.example.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper) {

        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponse saveCustomer(CustomerRequest request) {

        if (customerRepository.existsByEmail(request.getEmail())) {

            throw new EmailAlreadyExistsException(
                    "Email already exists");
        }

        if (customerRepository.existsByPhoneNumber(request.getPhoneNumber())) {

            throw new PhoneNumberAlreadyExistsException(
                    "Phone number already exists");
        }

        if (customerRepository.existsByGstNumber(request.getGstNumber())) {

            throw new GstNumberAlreadyExistsException(
                    "GST number already exists");
        }

        Customer customer = customerMapper.toEntity(request);

        customer.setCreatedAt(LocalDateTime.now());

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponse(savedCustomer);
    }
    @Override
    public CustomerResponse updateCustomer(Long customerId,
                                           CustomerRequest request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found"));

        if (!customer.getEmail().equals(request.getEmail())
                && customerRepository.existsByEmail(request.getEmail())) {

            throw new EmailAlreadyExistsException(
                    "Email already exists");
        }

        if (!customer.getPhoneNumber().equals(request.getPhoneNumber())
                && customerRepository.existsByPhoneNumber(request.getPhoneNumber())) {

            throw new PhoneNumberAlreadyExistsException(
                    "Phone number already exists");
        }

        if (!customer.getGstNumber().equals(request.getGstNumber())
                && customerRepository.existsByGstNumber(request.getGstNumber())) {

            throw new GstNumberAlreadyExistsException(
                    "GST number already exists");
        }

        customer.setCustomerName(request.getCustomerName());
        customer.setCompanyName(request.getCompanyName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setBillingAddress(request.getBillingAddress());
        customer.setShippingAddress(request.getShippingAddress());
        customer.setGstNumber(request.getGstNumber());
        customer.setReceivableAmount(request.getReceivableAmount());
        customer.setStatus(request.getStatus());
        customer.setUpdatedAt(LocalDateTime.now());

        Customer updatedCustomer = customerRepository.save(customer);

        return customerMapper.toResponse(updatedCustomer);
    }
    @Override
    public String deleteCustomer(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found"));

        customerRepository.delete(customer);

        return "Customer deleted successfully";
    }
    @Override
    public CustomerResponse getCustomerById(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found"));

        return customerMapper.toResponse(customer);
    }
    @Override
    public List<CustomerResponse> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(customerMapper::toResponse)
                .toList();
    }
    @Override
    public List<CustomerResponse> searchCustomer(String customerName) {

        List<Customer> customers =
                customerRepository.findByCustomerNameContainingIgnoreCase(customerName);

        if (customers.isEmpty()) {

            throw new CustomerNotFoundException(
                    "No customers found");

        }

        return customers.stream()
                .map(customerMapper::toResponse)
                .toList();
    }
}
