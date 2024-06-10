package com.example.ms.card.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping(path = {"/health", "/readiness"})
    public void checkHealth(){
    }
}
