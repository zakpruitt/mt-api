package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.repository.CreatureCardRepository;
import com.zakpruitt.mtapi.repository.SpellCardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardService {

    @Autowired
    private CreatureCardRepository creatureCardRepository;
    @Autowired
    private SpellCardRepository spellCardRepository;

    public ArrayList getCards() {
        ArrayList cards = new ArrayList();
        cards.add(creatureCardRepository.findAll());
        cards.add(spellCardRepository.findAll());
        return cards;
    }
}
