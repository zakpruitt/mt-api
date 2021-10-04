package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.StatusEffect.Trigger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TriggerRepository extends JpaRepository<Trigger, Long> {
    Trigger findByTriggerName(String triggerName);
}
