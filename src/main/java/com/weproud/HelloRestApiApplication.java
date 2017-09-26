package com.weproud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.sql.DataSource;

@Slf4j
@EnableCaching
@EnableAspectJAutoProxy
@SpringBootApplication
public class HelloRestApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HelloRestApiApplication.class, args);
	}

	@Autowired
	private DataSource dataSource;

	@Autowired
	private CacheManager cacheManager;

	@Override
	public void run(final String... args) throws Exception {
		log.debug("datasource: {}", dataSource);
		log.debug("cacheManager.getCacheNames(): {}", cacheManager.getCacheNames());
	}
}
