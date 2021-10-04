package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/cards")
    public Map<String, Object> findAllCards() {
        return cardService.getCards();
    }
}
