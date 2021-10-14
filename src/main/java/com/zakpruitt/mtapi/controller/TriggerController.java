package com.zakpruitt.mtapi.controller;

import com.zakpruitt.mtapi.domain.StatusEffect.Trigger;
import com.zakpruitt.mtapi.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/status")
public class TriggerController {

    @Autowired
    private TriggerService triggerService;

    @GetMapping("/triggers")
    public Map<String, Trigger> findAllTriggers() {
        return triggerService.getTriggers();
    }

    @GetMapping("/triggers/{name}")
    public Trigger findTriggerByName(@PathVariable String name) {
        return triggerService.getTriggerByName(name);
    }
}
