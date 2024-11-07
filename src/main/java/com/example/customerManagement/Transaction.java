package com.example.customerManagement;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ElementCollection
    @CollectionTable(name = "transaction_cookies", joinColumns = @JoinColumn(name = "transaction_id"))
    @MapKeyColumn(name = "cookie_type")
    @Column(name = "quantity")
    @Enumerated(EnumType.STRING)
    private Map<CookieType, Integer> cookiesQuantities;

    // Constructors, getters, and setters

    public Transaction() {}

    public Transaction(Double amount, LocalDateTime transactionDate, Customer customer, Map<CookieType, Integer> cookies) {
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.customer = customer;
        this.cookiesQuantities = cookiesQuantities;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}