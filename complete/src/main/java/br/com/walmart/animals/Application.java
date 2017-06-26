package br.com.walmart.animals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableCaching
@EnableMongoRepositories("br.com.walmart.animals.repository")
<<<<<<< HEAD
@ComponentScan({ "br.com.walmart.animals.service" })
@ConfigurationProperties("./properties/redis.properties")
=======
@ComponentScan({"br.com.walmart.animals.service"})
>>>>>>> parent of 49b9ba5... Update Project
public class Application {

  public static void main(String[] args) {	
     SpringApplication.run(Application.class, args);
     
} 
}
