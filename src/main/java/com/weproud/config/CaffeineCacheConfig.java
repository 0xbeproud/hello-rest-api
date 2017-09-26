package com.weproud.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Logan. k
 */
@Configuration
public class CaffeineCacheConfig {
    @Getter
    public enum Caches {
        CACHE_SERVICE(300),
        CACHE_CONTROLLER(300);

        Caches(int expireAfterWrite) {
            this.expireAfterWrite = expireAfterWrite;
        }

        Caches(int expireAfterWrite, int maximumSize) {
            this.expireAfterWrite = expireAfterWrite;
            this.maximumSize = maximumSize;
        }

        private int maximumSize = 50000;
        private int expireAfterWrite = 10;
    }

    @Bean
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = Arrays.stream(Caches.values())
                .map(cache -> new CaffeineCache(cache.name(),
                        Caffeine.newBuilder().recordStats()
                                .expireAfterWrite(cache.getExpireAfterWrite(), TimeUnit.SECONDS)
                                .maximumSize(cache.getMaximumSize())
                                .build()))
                .collect(Collectors.toList());

        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
