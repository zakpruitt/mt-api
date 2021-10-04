package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.Artifact;
import com.zakpruitt.mtapi.domain.StatusEffect.Buff;
import com.zakpruitt.mtapi.service.ArtifactService;
import com.zakpruitt.mtapi.service.BuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/status")
public class BuffController {

    @Autowired
    private BuffService buffService;

    @GetMapping("/buffs")
    public Map<String, Buff> findAllBuffs() {
        return buffService.getBuffs();
    }

    @GetMapping("/buffs/{name}")
    public Buff findBuffByName(@PathVariable String name) {
        return buffService.getBuffByName(name);
    }
}
