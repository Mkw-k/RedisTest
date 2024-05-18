package com.example.redis_test.controller;

import com.example.redis_test.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("/cache/{id}")
    public String getCacheData(@PathVariable String id) {
        return cacheService.getDataFromCache(id);
    }
}