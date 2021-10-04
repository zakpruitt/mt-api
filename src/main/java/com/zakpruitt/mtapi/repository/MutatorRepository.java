package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.Mutator;
import com.zakpruitt.mtapi.domain.StatusEffect.CardEffect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MutatorRepository extends JpaRepository<Mutator, Long> {
}
