package com.example.businesslogic.impl;

import com.example.businesslogic.api.AccountService;
import com.example.dataaccess.model.Account;
import com.example.dataaccess.repository.api.CRURepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "mokTxManager", propagation = Propagation.REQUIRES_NEW)
public class AccountServiceImpl implements AccountService {

    private final CRURepository<Account, Long> accountCruRepository;

    public AccountServiceImpl(@Qualifier("accountJpaRepository") CRURepository<Account, Long> accountCruRepository) {
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
