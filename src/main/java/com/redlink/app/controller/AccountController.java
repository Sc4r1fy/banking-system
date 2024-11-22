package com.redlink.app.controller;

import com.redlink.app.entity.Account;
import com.redlink.app.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id) {
        return accountService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.save(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Integer id, @RequestBody Account accountDetails) {
        return accountService.findById(id)
                .map(account -> {
                    account.setCurrency(accountDetails.getCurrency());
                    account.setBalance(accountDetails.getBalance());
                    Account updatedAccount = accountService.save(account);
                    return ResponseEntity.ok(updatedAccount);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Integer id) {
        return accountService.findById(id)
                .map(account -> {
                    accountService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/page")
    public Page<Account> getPaginatedAccounts(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        return accountService.findPaginated(page, size);
    }



}
