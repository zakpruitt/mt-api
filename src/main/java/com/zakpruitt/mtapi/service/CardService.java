package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.domain.Card.SpellCard;
import com.zakpruitt.mtapi.repository.CreatureCardRepository;
import com.zakpruitt.mtapi.repository.SpellCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"cards"})
public class CardService {

    @Autowired
    private CreatureCardRepository creatureCardRepository;
    @Autowired
    private SpellCardRepository spellCardRepository;

    @Cacheable
    public Map<String, Object> getCards() {
        Map<String, Object> allCards = new HashMap<>();
        allCards.putAll(creatureCardRepository.findAll().stream().collect(Collectors.toMap(CreatureCard::getCardName, Function.identity())));
        allCards.putAll(spellCardRepository.findAll().stream().collect(Collectors.toMap(SpellCard::getCardName, Function.identity())));
        return allCards;
    }

}
