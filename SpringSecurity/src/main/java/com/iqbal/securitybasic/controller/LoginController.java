package com.iqbal.securitybasic.controller;

import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());
        if(customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }
    }
}
