package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.StatusEffect.Buff;
import com.zakpruitt.mtapi.domain.StatusEffect.CardEffect;
import com.zakpruitt.mtapi.service.BuffService;
import com.zakpruitt.mtapi.service.CardEffectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/status")
public class CardEffectController {

    @Autowired
    private CardEffectService cardEffectService;

    @GetMapping("/card-effects")
    public Map<String, CardEffect> findAllCardEffects() {
        return cardEffectService.getCardEffects();
    }

    @GetMapping("/buffs/{name}")
    public CardEffect findCardEffectByName(@PathVariable String name) {
        return cardEffectService.getCardEffectByName(name);
    }
}
