package com.redlink.app.service;

import com.redlink.app.entity.Account;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IAccountService {

    List<Account> findAll();

    Optional<Account> findById(Integer id);

    Account save(Account account);

    void deleteById(Integer id);

    Page<Account> findPaginated(int page, int size);
}
