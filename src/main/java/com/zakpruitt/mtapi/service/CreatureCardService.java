package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.repository.CreatureCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CreatureCardService {

    @Autowired
    private CreatureCardRepository creatureCardRepository;

    public Map<String, CreatureCard> getCards() {
        Map<String, CreatureCard> allCreatureCards = new HashMap<>();
        allCreatureCards.putAll(creatureCardRepository.findAll().stream().collect(Collectors.toMap(CreatureCard::getCardName, Function.identity())));
        return allCreatureCards;
    }

    public CreatureCard getCreatureCardByName(String name) {
        return creatureCardRepository.findByCardName(name);
    }

    public CreatureCard saveCreatureCard(CreatureCard creatureCard) throws Exception {
        if (creatureCardRepository.findByCardName(creatureCard.getCardName()) != null) {
            throw new Exception("Card with that name already exists!");
        }
        return creatureCardRepository.save(creatureCard);
    }


}
