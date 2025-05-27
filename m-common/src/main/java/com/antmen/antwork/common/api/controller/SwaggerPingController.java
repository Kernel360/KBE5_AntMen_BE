package com.antmen.antwork.common.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerPingController {
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
