package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.StatusEffect.Effect;
import com.zakpruitt.mtapi.repository.EffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EffectService {

    @Autowired
    EffectRepository effectRepository;

    public Map<String, Effect> getEffects() {
        Map<String, Effect> allEffects = new HashMap<>();
        allEffects.putAll(effectRepository.findAll().stream().collect(Collectors.toMap(Effect::getEffectName, Function.identity())));
        return allEffects;
    }

    public Effect getEffectByName(String name) {
        return effectRepository.findByEffectName(name);
    }
}
