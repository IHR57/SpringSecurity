package com.iqbal.securitybasic.controller;

import com.iqbal.securitybasic.model.Accounts;
import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.repository.AccountsRepository;
import com.iqbal.securitybasic.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final CustomerRepository customerRepository;

    private final AccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam String email) {

        List<Customer> customers = customerRepository.findByEmail(email);
        if (customers != null && !customers.isEmpty()) {
            Accounts accounts = accountsRepository.findByCustomerId(customers.get(0).getId());
            return accounts;
        }
        return null;
    }
}