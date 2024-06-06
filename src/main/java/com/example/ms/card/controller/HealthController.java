package com.example.ms.card.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cards")
public class HealthController {
    @GetMapping(path = {"/health", "/readiness"})
    public String checkHealth(){
        return "SUCCESS";
    }
}
