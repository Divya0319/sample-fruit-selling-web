package com.sample.fruitsellingweb.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppStartupConfig {
	
	@Autowired
    private JwtConfig jwtConfig;

    @Bean
    public JwtConfig configureJwtParameters() {
        // Set the secret key
        jwtConfig.setSecretKey("adaafaasfdaadfAPKFSKPHJUGHJUYGGassdsffffKJJHGHGGJJHHGFSASDDSDA");

        // Set token validity in minutes
        jwtConfig.setJwtTokenValidityInMin(10);

        // Set the custom authorization header name
        jwtConfig.setAuthHeaderName("Authorization");
        
        // Set the custom token prefix
        jwtConfig.setRequiredTokenPrefix("Bearer");

        // Add excluded URLs
        jwtConfig.addExcludedUrl("/auth/login");   
        jwtConfig.addExcludedUrl("/v3/api-docs/*");
        jwtConfig.addExcludedUrl("/swagger-resources/**");
        jwtConfig.addExcludedUrl("/swagger-ui/*");
        jwtConfig.addExcludedUrl("/webjars/**");
        jwtConfig.addExcludedUrl("/favicon.ico");

        // Add authenticated URLs
        jwtConfig.addAuthenticatedUrl("/api/fruits/**");
        
        return jwtConfig;
    }
}
