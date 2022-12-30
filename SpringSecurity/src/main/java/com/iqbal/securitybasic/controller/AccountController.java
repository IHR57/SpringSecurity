package com.iqbal.securitybasic.controller;

import com.iqbal.securitybasic.model.Accounts;
import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam int id) {

        return accountsRepository.findByCustomerId(id);
    }
}