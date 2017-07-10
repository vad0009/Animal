package br.com.walmart.animals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("br.com.walmart.animals.repository")
@ComponentScans({ @ComponentScan({ "br.com.walmart.animals.service" }),
		@ComponentScan({ "br.com.walmart.animals.rabbit" }) })
@ConfigurationProperties("./properties/redis.properties")
@EnableCaching
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}
