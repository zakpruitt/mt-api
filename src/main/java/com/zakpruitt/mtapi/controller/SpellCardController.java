package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.domain.Card.SpellCard;
import com.zakpruitt.mtapi.repository.SpellCardRepository;
import com.zakpruitt.mtapi.service.CreatureCardService;
import com.zakpruitt.mtapi.service.SpellCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SpellCardController {
    @Autowired
    private SpellCardService spellCardService;

    @GetMapping("/cards")
    public Map<String, SpellCard> findAllCreatureCards() {
        return spellCardService.getCards();
    }

    @GetMapping("/cards/{name}")
    public SpellCard findSpellCardByName(@PathVariable String name) {
        return spellCardService.getSpellCardByName(name);
    }

    @PostMapping("/addSpellCard")
    public String addCreatureCard(@RequestBody SpellCard spellCard) {
        try {
            spellCardService.saveSpellCard(spellCard);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return String.format("SpellCard %s successfully added.", spellCard.getCardName());
    }
}
