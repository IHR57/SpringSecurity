package com.iqbal.securitybasic.controller;

import com.iqbal.securitybasic.model.Cards;
import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardsRepository cardsRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam int id) {

        return cardsRepository.findByCustomerId(id);
    }
}