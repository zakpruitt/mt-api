package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.Sin;
import com.zakpruitt.mtapi.domain.StatusEffect.CardEffect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinRepository extends JpaRepository<Sin, Long> {
    Sin findBySinName(String sinName);
}
