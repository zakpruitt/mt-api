package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.StatusEffect.Debuff;
import com.zakpruitt.mtapi.domain.StatusEffect.Effect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EffectRepository extends JpaRepository<Effect, Long> {
}
