package com.sample.fruitsellingweb.configs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sample.fruitsellingweb.filters.JwtAuthFilter;
import com.sample.fruitsellingweb.helpers.CustomAccessDeniedHandler;
import com.sample.fruitsellingweb.helpers.JwtAuthenticationEntryPoint;
import com.sample.fruitsellingweb.helpers.UrlPatternConverter;



@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntryPoint authEntryPoint;
	
	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private JwtAuthFilter authFilter;
	
	@Autowired
	private JwtConfig jwtConfig;
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> {
                jwtConfig.getExcludedUrls().forEach(url -> authorize.requestMatchers(url).permitAll());
                jwtConfig.getAuthenticatedUrls().forEach(url -> authorize.requestMatchers(url).hasRole("ADMIN"));
                authorize.anyRequest().authenticated();
            })
            .exceptionHandling(ex -> {
            	ex.authenticationEntryPoint(authEntryPoint);
            	ex.accessDeniedHandler(accessDeniedHandler);
            	})
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        List<String> convertedUrls = new ArrayList<>();
        convertedUrls.addAll(new UrlPatternConverter().processWildCardUrls(jwtConfig.getExcludedUrls()));
        
        for(String url : convertedUrls) {
        	if(!url.equals("/")) {
        		authFilter.addExcludedUrl(url);
        	}
        }

        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
	

}
