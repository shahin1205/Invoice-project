package com.example.customer.repository;

import com.example.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByGstNumber(String gstNumber);

    Optional<Customer> findByEmail(String email);

    List<Customer> findByCustomerNameContainingIgnoreCase(String customerName);

}
