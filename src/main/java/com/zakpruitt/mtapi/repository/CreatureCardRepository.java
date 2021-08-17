package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatureCardRepository extends JpaRepository<CreatureCard, Long> {
}
