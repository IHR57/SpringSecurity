package com.iqbal.securitybasic.controller;

import com.iqbal.securitybasic.model.Cards;
import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.repository.CardsRepository;
import com.iqbal.securitybasic.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardsRepository cardsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam String email) {

        List<Customer> customers = customerRepository.findByEmail(email);
        if (customers != null && !customers.isEmpty()) {
            return cardsRepository.findByCustomerId(customers.get(0).getId());
        }
        return null;
    }
}