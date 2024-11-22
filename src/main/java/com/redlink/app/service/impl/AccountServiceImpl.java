package com.redlink.app.service.impl;

import com.redlink.app.entity.Account;
import com.redlink.app.entity.User;
import com.redlink.app.repository.AccountRepository;
import com.redlink.app.repository.UserRepository;
import com.redlink.app.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account save(Account account) {

        User user = userRepository.findById(account.getUser().getId()).orElseThrow(() -> new RuntimeException("usuario con id : " + account.getUser().getId() + " no encontrado"));
        account.setUser(user);
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Page<Account> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accountRepository.findAll(pageable);
    }
}
