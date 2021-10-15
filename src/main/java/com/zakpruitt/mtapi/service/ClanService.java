package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.Clan;
import com.zakpruitt.mtapi.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"clans"})
public class ClanService {

    @Autowired
    ClanRepository clanRepository;

    @Cacheable
    public Map<String, Clan> getClans() {
        Map<String, Clan> allClans = new HashMap<>();
        allClans.putAll(clanRepository.findAll().stream().collect(Collectors.toMap(Clan::getClanName, Function.identity())));
        return allClans;
    }

    @Cacheable(key = "#name")
    public Clan getClanByName(String name) {
        return clanRepository.findByClanName(name);
    }
}
