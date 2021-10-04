package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.StatusEffect.Buff;
import com.zakpruitt.mtapi.domain.StatusEffect.CardEffect;
import com.zakpruitt.mtapi.repository.BuffRepository;
import com.zakpruitt.mtapi.repository.CardEffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CardEffectService {

    @Autowired
    CardEffectRepository cardEffectRepository;

    public Map<String, CardEffect> getCardEffects() {
        Map<String, CardEffect> allCardEffects = new HashMap<>();
        allCardEffects.putAll(cardEffectRepository.findAll().stream().collect(Collectors.toMap(CardEffect::getCardEffectName, Function.identity())));
        return allCardEffects;
    }

    public CardEffect getCardEffectByName(String name) {
        return cardEffectRepository.findByCardEffectName(name);
    }
}
