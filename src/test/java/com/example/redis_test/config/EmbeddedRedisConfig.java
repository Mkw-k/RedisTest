package com.example.redis_test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@Configuration
public class EmbeddedRedisConfig {

    @Bean
    public RedisServer redisServer() {
        // 포트 6379로 임베디드 Redis 서버를 시작합니다.
        return new RedisServer(6379);
    }
}