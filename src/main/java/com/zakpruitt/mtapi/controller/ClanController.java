package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.Clan;
import com.zakpruitt.mtapi.service.ClanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
public class ClanController {

    @Autowired
    private ClanService clanService;

    @GetMapping("/clans")
    public Map<String, Clan> findAllClans() {
        return clanService.getClans();
    }

    @GetMapping("/clans/{name}")
    public Clan findClanByName(@PathVariable String name) {
        return clanService.getClanByName(name);
    }
}
