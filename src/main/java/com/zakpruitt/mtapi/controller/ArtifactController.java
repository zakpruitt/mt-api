package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.Artifact;
import com.zakpruitt.mtapi.service.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.Cacheable;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
public class ArtifactController {

    @Autowired
    private ArtifactService artifactService;

    @GetMapping("/artifacts")
    public Map<String, Artifact> findAllArtifacts() {
        return artifactService.getArtifacts();
    }

    @GetMapping("/artifacts/{name}")
    public Artifact findArtifactByName(@PathVariable String name) {
        return artifactService.getArtifactByName(name);
    }
}
