package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.StatusEffect.CardEffect;
import com.zakpruitt.mtapi.repository.CardEffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"card-effects"})
public class CardEffectService {

    @Autowired
    CardEffectRepository cardEffectRepository;

    @Cacheable
    public Map<String, CardEffect> getCardEffects() {
        Map<String, CardEffect> allCardEffects = new HashMap<>();
        allCardEffects.putAll(cardEffectRepository.findAll().stream().collect(Collectors.toMap(CardEffect::getCardEffectName, Function.identity())));
        return allCardEffects;
    }

    @Cacheable(key = "#name")
    public CardEffect getCardEffectByName(String name) {
        return cardEffectRepository.findByCardEffectName(name);
    }
}
