package br.com.walmart.animals.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

public class SwaggerConfig extends WebMvcConfigurerAdapter{
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("animals").apiInfo(metaData()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/controller.*")).build();
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Spring Boot REST API", "Spring Boot REST API for Animals", "1.0",
				"Terms of service", new Contact("Vitor Desiderio", null, null), "Apache License Version 2.0",
				"https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}	
}
