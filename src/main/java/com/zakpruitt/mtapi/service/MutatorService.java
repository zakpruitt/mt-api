package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.Mutator;
import com.zakpruitt.mtapi.repository.MutatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"mutators"})
public class MutatorService {

    @Autowired
    MutatorRepository mutatorRepository;

    @Cacheable
    public Map<String, Mutator> getMutators() {
        Map<String, Mutator> allMutators = new HashMap<>();
        allMutators.putAll(mutatorRepository.findAll().stream().collect(Collectors.toMap(Mutator::getMutatorName, Function.identity())));
        return allMutators;
    }

    @Cacheable(key = "#name")
    public Mutator getMutatorByName(String name) {
        return mutatorRepository.findByMutatorName(name);
    }
}
