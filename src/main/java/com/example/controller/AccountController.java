package com.example.controller;

import com.example.model.Account;
import com.example.repository.impl.AccountRepository;
import com.example.repository.impl.AccountRepository2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;
    private final AccountRepository2 accountRepository2;

    @GetMapping
    ResponseEntity<?> findAll() {
        return ResponseEntity.ok(accountRepository.findAll());
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody Account account) {
        return ResponseEntity.ok(accountRepository.save(account));
    }

    @PostMapping("/2")
    ResponseEntity<?> create2(@RequestBody Account account) {
        return ResponseEntity.ok(accountRepository2.save(account));
    }

}
