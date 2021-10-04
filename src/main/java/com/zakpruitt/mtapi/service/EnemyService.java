package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.Enemy;
import com.zakpruitt.mtapi.repository.EnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EnemyService {

    @Autowired
    EnemyRepository enemyRepository;

    public Map<String, Enemy> getEnemies() {
        Map<String, Enemy> allEnemies = new HashMap<>();
        allEnemies.putAll(enemyRepository.findAll().stream().collect(Collectors.toMap(Enemy::getEnemyName, Function.identity())));
        return allEnemies;
    }

    public Enemy getEnemyByName(String name) {
        return enemyRepository.findByEnemyName(name);
    }
}
