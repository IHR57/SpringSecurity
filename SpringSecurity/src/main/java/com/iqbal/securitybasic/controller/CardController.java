package com.iqbal.securitybasic.controller;

import com.iqbal.securitybasic.model.Cards;
import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardsRepository cardsRepository;

    @PostMapping("/myCards")
    public List<Cards> getCardDetails(@RequestBody Customer customer) {

        return cardsRepository.findByCustomerId(customer.getId());
    }
}