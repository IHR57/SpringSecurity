package com.iqbal.securitybasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @GetMapping("/myCard")
    public String getMyCards() {
        return "My Card ...";
    }
}