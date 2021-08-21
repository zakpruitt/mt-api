package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.Modifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardEffectRepository extends JpaRepository<Modifier, Long> {
}
