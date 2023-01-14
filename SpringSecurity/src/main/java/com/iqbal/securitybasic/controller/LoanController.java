package com.iqbal.securitybasic.controller;

import com.iqbal.securitybasic.model.Loans;
import com.iqbal.securitybasic.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestParam int id) {

        return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
    }
}