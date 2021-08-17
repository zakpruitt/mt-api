package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/cards")
    public ArrayList findAllCards() {
        return cardService.getCards();
    }

//    @GetMapping("/productById/{id}")
//    public Product findProductById(@PathVariable String id) {
//        return cardService.getProductById(id);
//    }
//
//    @GetMapping("/product/{name}")
//    public Product findProductByName(@PathVariable String name) {
//        return cardService.getProductByName(name);
//    }
}
