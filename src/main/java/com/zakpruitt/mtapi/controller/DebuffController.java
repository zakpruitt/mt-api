package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.Clan;
import com.zakpruitt.mtapi.domain.StatusEffect.Debuff;
import com.zakpruitt.mtapi.service.ClanService;
import com.zakpruitt.mtapi.service.DebuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/status")
public class DebuffController {

    @Autowired
    private DebuffService debuffService;

    @GetMapping("/debuffs")
    public Map<String, Debuff> findAllDebuffs() {
        return debuffService.getDebuffs();
    }

    @GetMapping("/debuffs/{name}")
    public Debuff findDebuffByName(@PathVariable String name) {
        return debuffService.getDebuffByName(name);
    }
}
