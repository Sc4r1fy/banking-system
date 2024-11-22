package com.redlink.app.service;

import com.redlink.app.entity.Transaction;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {

    List<Transaction> findAll();

    Optional<Transaction> findById(Integer id);

    Transaction save(Transaction transaction);

    void deleteById(Integer id);

    Page<Transaction> findPaginated(int page, int size);
}
