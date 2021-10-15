package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.StatusEffect.Buff;
import com.zakpruitt.mtapi.repository.BuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"buffs"})
public class BuffService {

    @Autowired
    BuffRepository buffRepository;

    @Cacheable
    public Map<String, Buff> getBuffs() {
        Map<String, Buff> allBuffs = new HashMap<>();
        allBuffs.putAll(buffRepository.findAll().stream().collect(Collectors.toMap(Buff::getBuffName, Function.identity())));
        return allBuffs;
    }

    @Cacheable(key = "#name")
    public Buff getBuffByName(String name) {
        return buffRepository.findByBuffName(name);
    }
}
