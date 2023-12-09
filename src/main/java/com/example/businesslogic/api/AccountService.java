package com.example.businesslogic.api;

import com.example.dataaccess.model.Account;

import java.util.List;

public interface AccountService {

    Account save(Account account);

    List<Account> findAll();
}
