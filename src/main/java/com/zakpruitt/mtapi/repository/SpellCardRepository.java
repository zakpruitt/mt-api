package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.Card.SpellCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpellCardRepository extends JpaRepository<SpellCard, String> {
}
