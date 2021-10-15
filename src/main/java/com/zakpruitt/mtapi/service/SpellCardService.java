package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.Card.SpellCard;
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
@CacheConfig(cacheNames = {"spell-cards"})
public class SpellCardService {
    @Autowired
    private SpellCardRepository spellCardRepository;

    @Cacheable
    public Map<String, SpellCard> getCards() {
        Map<String, SpellCard> allSpellCards = new HashMap<>();
        allSpellCards.putAll(spellCardRepository.findAll().stream().collect(Collectors.toMap(SpellCard::getCardName, Function.identity())));
        return allSpellCards;
    }

    @Cacheable(key = "#name")
    public SpellCard getSpellCardByName(String name) {
        return spellCardRepository.findByCardName(name);
    }
}
