package com.example.customerManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<?> createTransaction(
            @PathVariable Long customerId,
            @RequestBody TransactionRequest transactionRequest) {
        try {
            Transaction transaction = transactionService.addTransaction(
                    customerId,
                    transactionRequest.getAmount(),
                    transactionRequest.getCookieQuantities()
            );
            return ResponseEntity.ok(transaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}