package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.Artifact;
import com.zakpruitt.mtapi.repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"artifacts"})
public class ArtifactService {

    @Autowired
    ArtifactRepository artifactRepository;

    @Cacheable
    public Map<String, Artifact> getArtifacts() {
        Map<String, Artifact> allArtifacts = new HashMap<>();
        allArtifacts.putAll(artifactRepository.findAll().stream().collect(Collectors.toMap(Artifact::getArtifactName, Function.identity())));
        return allArtifacts;
    }

    @Cacheable(key = "#name")
    public Artifact getArtifactByName(String name) {
        return artifactRepository.findByArtifactName(name);
    }
}
