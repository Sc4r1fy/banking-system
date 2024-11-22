package com.redlink.app.controller;

import com.redlink.app.entity.Transaction;
import com.redlink.app.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Integer id) {
        return transactionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.save(transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Integer id) {
        return transactionService.findById(id)
                .map(transaction -> {
                    transactionService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/page")
    public Page<Transaction> getPaginatedTransactions(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        return transactionService.findPaginated(page, size);
    }
}
