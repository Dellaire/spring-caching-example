package de.clumsystuff.spring.caching.example;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
public class CacheConfiguration {

	@Bean
	public CacheManager cacheManager() {

		CaffeineCache shortCache = new CaffeineCache("shortCache",
				Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build());
		CaffeineCache longCache = new CaffeineCache("longCache",
				Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).build());
		SimpleCacheManager manager = new SimpleCacheManager();
		manager.setCaches(Arrays.asList(shortCache, longCache));
		return manager;
	}
}
