package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.StatusEffect.Buff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuffRepository extends JpaRepository<Buff, Long> {
    Buff findByBuffName(String buffName);
}
