package com.example.businesslogic.impl;

import com.example.businesslogic.api.AccountService;
import com.example.dataaccess.model.Account;
import com.example.dataaccess.repository.api.CRURepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "mozTxManager", propagation = Propagation.REQUIRES_NEW)
public class AccountMozServiceImpl implements AccountService {

    private final CRURepository<Account, Long> accountCruRepository;

    public AccountMozServiceImpl(@Qualifier("accountMozJpaRepository") CRURepository<Account, Long> accountCruRepository) {
        this.accountCruRepository = accountCruRepository;
    }

    @Override
    public Account save(Account account) {
        return accountCruRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountCruRepository.findAll();
    }
}
