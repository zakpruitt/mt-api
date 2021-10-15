package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.Sin;
import com.zakpruitt.mtapi.repository.SinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"sins"})
public class SinService {

    @Autowired
    SinRepository sinRepository;

    @Cacheable
    public Map<String, Sin> getSins() {
        Map<String, Sin> allSins = new HashMap<>();
        allSins.putAll(sinRepository.findAll().stream().collect(Collectors.toMap(Sin::getSinName, Function.identity())));
        return allSins;
    }

    @Cacheable(key = "#name")
    public Sin getSinByName(String name) {
        return sinRepository.findBySinName(name);
    }
}
