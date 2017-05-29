package br.com.walmart.animals.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("br.com.walmart.animals.repository")
@ComponentScan({"br.com.walmart.animals.service"})
public class Application {
	
    public static void main(String[] args) {
   	
       SpringApplication.run(Application.class, args);
   }
   
}

