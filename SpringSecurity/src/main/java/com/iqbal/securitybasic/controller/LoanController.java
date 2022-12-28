package com.iqbal.securitybasic.controller;

import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.model.Loans;
import com.iqbal.securitybasic.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanRepository loanRepository;

    @PostMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestBody Customer customer) {

        return loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getId());
    }
}