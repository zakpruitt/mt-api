package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.Enemy;
import com.zakpruitt.mtapi.service.EnemyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
public class EnemyController {

    @Autowired
    private EnemyService enemyService;

    @GetMapping("/enemies")
    public Map<String, Enemy> findAllEnemies() {
        return enemyService.getEnemies();
    }

    @GetMapping("/enemies/{name}")
    public Enemy findEnemyByName(@PathVariable String name) {
        return enemyService.getEnemyByName(name);
    }
}