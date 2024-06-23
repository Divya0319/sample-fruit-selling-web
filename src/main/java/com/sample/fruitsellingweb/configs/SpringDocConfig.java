package com.sample.fruitsellingweb.configs;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringDocConfig.class);
	
	@Autowired
	private JwtConfig jwtConfig;

	
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
        		.addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("bearerAuth", createJWTAuthScheme())) // for JWT Token
                .security(List.of(new SecurityRequirement().addList("bearerAuth")))
                .info(new Info().title("Sample Fruit Store")
                        .description("For SpringDoc config")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
                
        return openAPI;
    }
	
	@Bean
	public OpenApiCustomizer customizeOpenApi() {
        return openApi -> {
 
            Paths paths = openApi.getPaths();
            if (paths != null) {
                paths.forEach((path, pathItem) -> {
                    pathItem.readOperations().forEach(operation -> {
                        if ("generateToken".equals(operation.getOperationId())) {
                            int validityInMin = jwtConfig.getJwtTokenValidityInMin();
                            String summary = "Generates a unique JWT auth token, valid for " + validityInMin + " min.";

                            if (validityInMin > 59) {
                                int hours = validityInMin / 60;
                                int mins = validityInMin % 60;
                                
                                if(mins == 0) {
                                	summary = "Generates a unique JWT auth token, valid for " + hours + " hrs ";
                                }
                                else {
                                	summary = "Generates a unique JWT auth token, valid for " + hours + " hrs " + mins + " mins";
                                }
                            }
                            logger.info("Updating summary for generateToken: {}", summary);
                            operation.setSummary(summary);
                        }
                    });
                });
            }
        };
    }

	private SecurityScheme createJWTAuthScheme() {
		return new SecurityScheme().type(SecurityScheme.Type.APIKEY) // APIKEY or HTTP(depending on requirement)
				.name("Authorization").description("JWT auth description")
//    			.bearerFormat("JWT")
				.in(SecurityScheme.In.HEADER);
	}

}
