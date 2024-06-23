package com.sample.fruitsellingweb.configs;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {
	
	@Bean
	public GroupedOpenApi api() {
		GroupedOpenApi groupOpenApi =  GroupedOpenApi.builder()
                .group("sample-fruit-selling-api")
                .packagesToScan("com.sample.fruitsellingweb.controllers")
                .build();
		return groupOpenApi;
	}
	
	@Bean
    public OpenAPI fruitStoreOpenAPI() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info().title("Sample Fruit Store")
                        .description("For SpringDoc config")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
                
        return openAPI;
    }

}
