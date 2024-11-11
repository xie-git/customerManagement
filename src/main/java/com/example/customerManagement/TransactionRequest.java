package com.example.customerManagement;

import java.util.Map;

public class TransactionRequest {

    private Double amount;
    private Map<CookieType, Integer> cookieQuantities;

    // Constructors, getters, and setters

    public TransactionRequest() {}

    public TransactionRequest(Double amount, Map<CookieType, Integer> cookieQuantities) {
        this.amount = amount;
        this.cookieQuantities = cookieQuantities;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Map<CookieType, Integer> getCookieQuantities() {
        return cookieQuantities;
    }

    public void setCookieQuantities(Map<CookieType, Integer> cookieQuantities) {
        this.cookieQuantities = cookieQuantities;
    }
}
