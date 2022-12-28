package com.iqbal.securitybasic.controller;

import com.iqbal.securitybasic.model.Accounts;
import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountsRepository accountsRepository;

    @PostMapping("/myAccount")
    public Accounts getAccountDetails(@RequestBody Customer customer) {

        return accountsRepository.findByCustomerId(customer.getId());
    }
}