package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.StatusEffect.Debuff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebuffRepository extends JpaRepository<Debuff, Long> {
}
