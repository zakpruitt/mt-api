package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.Clan;
import com.zakpruitt.mtapi.domain.StatusEffect.CardEffect;
import com.zakpruitt.mtapi.repository.CardEffectRepository;
import com.zakpruitt.mtapi.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ClanService {

    @Autowired
    ClanRepository clanRepository;

    public Map<String, Clan> getClans() {
        Map<String, Clan> allClans = new HashMap<>();
        allClans.putAll(clanRepository.findAll().stream().collect(Collectors.toMap(Clan::getClanName, Function.identity())));
        return allClans;
    }

    public Clan getClanByName(String name) {
        return clanRepository.findByClanName(name);
    }
}
