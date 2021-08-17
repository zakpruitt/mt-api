package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.repository.CreatureCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatureCardService {

    @Autowired
    private CreatureCardRepository creatureCardRepository;

    public List<CreatureCard> getCards() {
        return creatureCardRepository.findAll();
    }

    public CreatureCard saveCreatureCard(CreatureCard creatureCard) {
        return creatureCardRepository.save(creatureCard);
    }
}
