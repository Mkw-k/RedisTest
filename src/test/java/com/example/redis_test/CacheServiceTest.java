package com.example.redis_test;

import com.example.redis_test.config.EmbeddedRedisConfig;
import com.example.redis_test.service.CacheService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(EmbeddedRedisConfig.class)
@ActiveProfiles("test")
public class CacheServiceTest {

    private final CacheManager cacheManager;
    private final CacheService cacheService;

    public CacheServiceTest(CacheManager cacheManager, CacheService cacheService) {
        this.cacheManager = cacheManager;
        this.cacheService = cacheService;
    }

    @BeforeEach
    public void setup() {
        cacheManager.getCache("sampleCache").clear();
    }

    @Test
    public void testCacheable() {
        String id = "123";
        String expectedValue = "Data for ID: " + id;

        // 캐시가 없는 상태에서 데이터 가져오기
        String value1 = cacheService.getDataFromCache(id);
        assertThat(value1).isEqualTo(expectedValue);
        System.out.println("value1 = " + value1);

        // 캐시된 데이터를 가져오는지 확인 (메서드 호출 없이 캐시에서 가져와야 함)
        String value2 = cacheService.getDataFromCache(id);
        assertThat(value2).isEqualTo(expectedValue);
        System.out.println("value2 = " + value2);

        // 캐시 키를 확인하여 데이터가 저장되었는지 확인
        assertThat(cacheManager.getCache("sampleCache").get("123").get()).isEqualTo(expectedValue);
    }
}