package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.service.CreatureCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1/creature")
public class CreatureCardController {

    @Autowired
    private CreatureCardService creatureCardService;

    @GetMapping("/cards")
    public List<CreatureCard> findAllCreatureCards() {
        return creatureCardService.getCards();
    }

    @GetMapping("/cards")
    public CreatureCard findCreatureCardById(@PathVariable long id) {
        return cardService.getProductById(id);
    }

    @GetMapping("/cards/{name}")
    public Product findProductByName(@PathVariable String name) {
        return cardService.getProductByName(name);
    }

    @PostMapping("/addCreatureCard")
    public CreatureCard addCreatureCard(@RequestBody CreatureCard product) {
        return creatureCardService.saveCreatureCard(product);
    }


}
