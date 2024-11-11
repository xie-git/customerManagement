package com.example.customerManagement.service;

import com.example.customerManagement.entity.CookieType;
import com.example.customerManagement.entity.Customer;
import com.example.customerManagement.entity.Transaction;
import com.example.customerManagement.repository.CustomerRepository;
import com.example.customerManagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    public Transaction addTransaction(Long customerId, Double amount, Map<CookieType, Integer> cookieQuantities) {
        // Check if customer exists
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Create the transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionDateAdded(LocalDateTime.now());
        transaction.setTransactionDate(LocalDate.now());
        transaction.setCustomer(customer);
        transaction.setCookiesQuantities(cookieQuantities); // Corrected variable name

        // Save and return the transaction
        return transactionRepository.save(transaction);
    }
}