package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.StatusEffect.CardEffect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardEffectRepository extends JpaRepository<CardEffect, Long> {
}
