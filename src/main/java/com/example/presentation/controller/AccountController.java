package com.example.presentation.controller;

import com.example.businesslogic.api.AccountService;
import com.example.dataaccess.model.Account;
import com.example.dataaccess.repository.api.CRURepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService mokAccountService;
    private final AccountService mozAccountService;

    public AccountController(@Qualifier("accountServiceImpl") AccountService mokAccountService,
                             @Qualifier("accountMozServiceImpl") AccountService mozAccountService) {
        this.mokAccountService = mokAccountService;
        this.mozAccountService = mozAccountService;
    }

    @GetMapping("/mok")
    List<Account> findAllAsMok() {
        return mokAccountService.findAll();
    }

    @GetMapping("/moz")
    List<Account> findAllAsMoz() {
        return mozAccountService.findAll();
    }

    //No permissions in db for that
    @PostMapping("/mok")
    Account createAsMok(@RequestBody Account account) {
        return mokAccountService.save(account);
    }

    @PostMapping("moz")
    Account createAsMoz(@RequestBody Account account) {
        return mozAccountService.save(account);
    }
}
