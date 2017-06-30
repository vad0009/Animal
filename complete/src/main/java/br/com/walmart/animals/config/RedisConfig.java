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

	// Conectando com o servidor do redis
	@Bean
	public JedisConnectionFactory redisConnectionFactory() throws IOException {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

		// Defaults
		redisConnectionFactory.setHostName("redis.server.hostName");
		redisConnectionFactory.setPort("redis.server.port".charAt(0));
		return redisConnectionFactory;
	}

	// Setando o template no servidor do redis
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(cf);
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

		// Numeros de segundos antes de expirar. Por defaults o numero de
		// segundos é (0), ou seja ilimitado.
		cacheManager.setDefaultExpiration("redis.server.defaultExpiration".charAt(0));
		return cacheManager;
	}

}
