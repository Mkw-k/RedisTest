package com.example.redis_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    private final CacheManager cacheManager;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Cacheable(value = "sampleCache", key = "#id")
    public String getDataFromCache(String id) {
        // 이 메서드가 캐시되지 않은 경우에만 실행됩니다.
        simulateSlowService(); // 데이터베이스나 외부 API 호출을 시뮬레이션합니다.
        return "Data for ID: " + id;
    }

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}