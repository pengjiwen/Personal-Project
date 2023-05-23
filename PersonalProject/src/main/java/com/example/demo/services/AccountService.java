package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;

@Service
public class AccountService {
    private AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public boolean checkAccount(String username, String password) {
        Account account = repository.findByUsername(username);
        return account != null && account.getPassword().equals(password);
    }

    public boolean newAccount(String username, String password) {
        if (repository.findByUsername(username) == null) {
            repository.save(new Account(username, password));
            return true;
        } else {
            return false;
        }
    }
}
