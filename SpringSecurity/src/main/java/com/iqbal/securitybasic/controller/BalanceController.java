package com.iqbal.securitybasic.controller;

import com.iqbal.securitybasic.model.AccountTransactions;
import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.repository.AccountTransactionsRepository;
import com.iqbal.securitybasic.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTransactionsRepository accountTransactionsRepository;
    private final CustomerRepository customerRepository;

    @PostMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestBody String email) {

        List<Customer> customers = customerRepository.findByEmail(email);
        if (customers != null && !customers.isEmpty()) {
            return accountTransactionsRepository.
                    findByCustomerIdOrderByTransactionDtDesc(customers.get(0).getId());
        }
        return null;
    }
}