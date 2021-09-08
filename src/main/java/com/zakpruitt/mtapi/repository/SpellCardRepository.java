package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.domain.Card.SpellCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface SpellCardRepository extends JpaRepository<SpellCard, Long> {
    SpellCard findByCardName(String name);
}
