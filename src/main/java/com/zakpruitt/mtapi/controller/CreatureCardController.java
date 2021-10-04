package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.service.CreatureCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/api/v1")
@RequestMapping(path = "/api/v1")
public class CreatureCardController {

    @Autowired
    private CreatureCardService creatureCardService;

    @GetMapping("/creature-cards")
    public Map<String, CreatureCard> findAllCreatureCards() {
        return creatureCardService.getCards();
    }

    @GetMapping("/creature-card/{name}")
    public CreatureCard findCreatureCardByName(@PathVariable String name) {
        return creatureCardService.getCreatureCardByName(name);
    }
}
