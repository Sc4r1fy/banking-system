package com.redlink.app.service.impl;

import com.redlink.app.entity.Account;
import com.redlink.app.entity.User;
import com.redlink.app.repository.AccountRepository;
import com.redlink.app.repository.UserRepository;
import com.redlink.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<Account> findUserAccounts(Integer userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accountRepository.findByUserId(userId, pageable);
    }
}
