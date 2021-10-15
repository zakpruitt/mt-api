package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.StatusEffect.Debuff;
import com.zakpruitt.mtapi.repository.DebuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"debuffs"})
public class DebuffService {

    @Autowired
    DebuffRepository debuffRepository;

    @Cacheable
    public Map<String, Debuff> getDebuffs() {
        Map<String, Debuff> allClans = new HashMap<>();
        allClans.putAll(debuffRepository.findAll().stream().collect(Collectors.toMap(Debuff::getDebuffName, Function.identity())));
        return allClans;
    }

    @Cacheable(key = "#name")
    public Debuff getDebuffByName(String name) {
        return debuffRepository.findByDebuffName(name);
    }
}
