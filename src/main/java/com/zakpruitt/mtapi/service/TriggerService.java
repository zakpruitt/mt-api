package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.StatusEffect.Trigger;
import com.zakpruitt.mtapi.repository.TriggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"triggers"})
public class TriggerService {

    @Autowired
    TriggerRepository triggerRepository;

    @Cacheable
    public Map<String, Trigger> getTriggers() {
        Map<String, Trigger> allSpellCards = new HashMap<>();
        allSpellCards.putAll(triggerRepository.findAll().stream().collect(Collectors.toMap(Trigger::getTriggerName, Function.identity())));
        return allSpellCards;
    }

    @Cacheable(key = "#name")
    public Trigger getTriggerByName(String name) {
        return triggerRepository.findByTriggerName(name);
    }
}
