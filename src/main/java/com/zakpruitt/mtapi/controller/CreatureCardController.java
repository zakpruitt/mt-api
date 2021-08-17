package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.service.CardService;
import com.zakpruitt.mtapi.service.CreatureCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatureCardController {

    @Autowired
    private CreatureCardService creatureCardService;

    @PostMapping("/addProduct")
    public CreatureCard addProduct(@RequestBody CreatureCard product) {
        return creatureCardService.saveCreatureCard(product);
    }
}
