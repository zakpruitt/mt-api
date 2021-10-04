package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.Mutator;
import com.zakpruitt.mtapi.domain.Sin;
import com.zakpruitt.mtapi.service.MutatorService;
import com.zakpruitt.mtapi.service.SinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
public class SinController {

    @Autowired
    private SinService sinService;

    @GetMapping("/sins")
    public Map<String, Sin> findAllMutators() {
        return sinService.getSins();
    }

    @GetMapping("/sin/{name}")
    public Sin findSinByName(@PathVariable String name) {
        return sinService.getSinByName(name);
    }
}
