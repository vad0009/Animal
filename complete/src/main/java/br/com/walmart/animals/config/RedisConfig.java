package br.com.walmart.animals.config;

import java.io.IOException;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
	public JedisConnectionFactory redisConnectionFactory() throws IOException {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

		
		redisConnectionFactory.setHostName("redis.server.hostName");
		redisConnectionFactory.setPort("redis.server.port".toString().charAt(0));
		return redisConnectionFactory;
	}

	
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(cf);
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

		cacheManager.setDefaultExpiration("redis.server.defaultExpiration".charAt(0));
		return cacheManager;
	}

}
