package com.example.redis_test.controller;

import com.example.redis_test.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/set")
    public String setValue(@RequestParam String key, @RequestParam String value) {
        redisService.setValue(key, value);
        return "Value set successfully!";
    }

    @GetMapping("/get")
    public String getValue(@RequestParam String key) {
        return "Value: " + redisService.getValue(key);
    }
}