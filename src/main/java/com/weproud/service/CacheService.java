package com.weproud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Logan. k
 */
@Slf4j
@CacheConfig(cacheNames = {"CACHE_SERVICE"})
@Service
public class CacheService {

    @Cacheable
    public int getWithCache() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return IntStream.range(0, 1000000).sum();
    }

    public int getWithoutCache() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return IntStream.range(0, 1000000).sum();
    }
}
