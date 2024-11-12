package com.example.customerManagement.service;

import com.example.customerManagement.entity.Customer;
import com.example.customerManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable; // Correct import for Pageable

import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        if (customerRepository.findByPhone(customer.getPhone()).isPresent()) {
            throw new IllegalArgumentException("Phone is already registered");
        }
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }


    public Page<Customer> getAllCustomers(String name, Pageable pageable) {
        if (name != null && !name.isEmpty()) {
            return customerRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            return customerRepository.findAll(pageable);
        }
    }

    public Optional<Customer> updateCustomer(Long id, Customer customer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(customer.getName());
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setPhone(customer.getPhone());
                    // Add more fields if needed
                    return customerRepository.save(existingCustomer);
                });
    }


    public Optional<Customer> partialUpdateCustomer(Long id, Map<String, Object> updates) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    updates.forEach((field, value) -> {
                        switch (field) {
                            case "name":
                                existingCustomer.setName((String) value);
                                break;
                            case "email":
                                existingCustomer.setEmail((String) value);
                                break;
                            case "phone":
                                existingCustomer.setPhone((String) value);
                                break;
                            // Add more cases for each updatable field
                        }
                    });
                    return customerRepository.save(existingCustomer);
                });
    }
}
