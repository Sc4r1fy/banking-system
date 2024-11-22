package com.redlink.app.service;

import com.redlink.app.entity.Account;
import com.redlink.app.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAll();

    Optional<User> findById(Integer id);

    User save(User user);

    void deleteById(Integer id);

    Page<Account> findUserAccounts(Integer userId, int page, int size);
}
