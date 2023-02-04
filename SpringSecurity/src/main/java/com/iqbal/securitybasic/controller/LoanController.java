package com.iqbal.securitybasic.controller;

import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.model.Loans;
import com.iqbal.securitybasic.repository.CustomerRepository;
import com.iqbal.securitybasic.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestParam String email) {

        List<Customer> customers = customerRepository.findByEmail(email);
        if (customers != null && !customers.isEmpty()) {
            return loanRepository.findByCustomerIdOrderByStartDtDesc(customers.get(0).getId());
        }
        return null;
    }
}