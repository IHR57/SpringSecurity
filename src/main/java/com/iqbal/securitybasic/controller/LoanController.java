package com.iqbal.securitybasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @GetMapping("/myLoans")
    public String getMyLoans() {
        return "My Loans ...";
    }
}