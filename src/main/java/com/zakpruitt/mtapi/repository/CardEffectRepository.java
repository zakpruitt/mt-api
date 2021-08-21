package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.Effect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardEffectRepository extends JpaRepository<Effect, Long> {
}
