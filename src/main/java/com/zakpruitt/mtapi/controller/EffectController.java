package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.StatusEffect.Effect;
import com.zakpruitt.mtapi.service.EffectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/status")
public class EffectController {

    @Autowired
    private EffectService effectService;

    @GetMapping("/effects")
    public Map<String, Effect> findAllEffects() {
        return effectService.getEffects();
    }

    @GetMapping("/effect/{name}")
    public Effect findEffectByName(@PathVariable String name) {
        return effectService.getEffectByName(name);
    }
}
