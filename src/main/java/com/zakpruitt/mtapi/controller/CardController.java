package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/v1")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/cards")
    public ArrayList findAllCards() {
        return cardService.getCards();
    }
}
