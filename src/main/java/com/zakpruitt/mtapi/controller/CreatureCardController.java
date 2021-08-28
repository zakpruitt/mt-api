package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.service.CreatureCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController("/api/v1")
@RequestMapping(path = "/api/v1/creature")
public class CreatureCardController {

    @Autowired
    private CreatureCardService creatureCardService;

    @GetMapping("/cards")
    public Map<String, CreatureCard> findAllCreatureCards() {
        return creatureCardService.getCards();
    }

    @GetMapping("/cards/{name}")
    public CreatureCard findProductByName(@PathVariable String name) {
        return creatureCardService.getCreatureCardByName(name);
    }

    @PostMapping("/addCreatureCard")
    public CreatureCard addCreatureCard(@RequestBody CreatureCard creatureCard) {
        return creatureCardService.saveCreatureCard(creatureCard);
    }


}
