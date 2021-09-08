package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.domain.Card.SpellCard;
import com.zakpruitt.mtapi.repository.CreatureCardRepository;
import com.zakpruitt.mtapi.repository.SpellCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SpellCardService {
    @Autowired
    private SpellCardRepository spellCardRepository;

    public Map<String, SpellCard> getCards() {
        Map<String, SpellCard> allSpellCards = new HashMap<>();
        allSpellCards.putAll(spellCardRepository.findAll().stream().collect(Collectors.toMap(SpellCard::getCardName, Function.identity())));
        return allSpellCards;
    }

    public SpellCard getSpellCardByName(String name) {
        return spellCardRepository.findByCardName(name);
    }

    public SpellCard saveSpellCard(SpellCard spellCard) throws Exception {
        if (spellCardRepository.findByCardName(spellCard.getCardName()) != null) {
            throw new Exception("Card with that name already exists!");
        }
        return spellCardRepository.save(spellCard);
    }
}
