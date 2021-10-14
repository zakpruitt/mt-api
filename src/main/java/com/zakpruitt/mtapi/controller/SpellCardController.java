package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.Card.SpellCard;
import com.zakpruitt.mtapi.service.SpellCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
public class SpellCardController {
    @Autowired
    private SpellCardService spellCardService;

    @GetMapping("/spell-cards")
    public Map<String, SpellCard> findAllSpellCards() {
        return spellCardService.getCards();
    }

    @GetMapping("/spell-cards/{name}")
    public SpellCard findSpellCardByName(@PathVariable String name) {
        return spellCardService.getSpellCardByName(name);
    }
}
