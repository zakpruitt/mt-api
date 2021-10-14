package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.Mutator;
import com.zakpruitt.mtapi.service.MutatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
public class MutatorController {

    @Autowired
    private MutatorService mutatorService;

    @GetMapping("/mutators")
    public Map<String, Mutator> findAllMutators() {
        return mutatorService.getMutators();
    }

    @GetMapping("/mutators/{name}")
    public Mutator findMutatorByName(@PathVariable String name) {
        return mutatorService.getMutatorByName(name);
    }
}
