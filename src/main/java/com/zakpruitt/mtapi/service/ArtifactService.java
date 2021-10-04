package com.zakpruitt.mtapi.service;

import com.zakpruitt.mtapi.domain.Artifact;
import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArtifactService {

    @Autowired
    ArtifactRepository artifactRepository;

    public Map<String, Artifact> getArtifacts() {
        Map<String, Artifact> allArtifacts = new HashMap<>();
        allArtifacts.putAll(artifactRepository.findAll().stream().collect(Collectors.toMap(Artifact::getArtifactName, Function.identity())));
        return allArtifacts;
    }
}
