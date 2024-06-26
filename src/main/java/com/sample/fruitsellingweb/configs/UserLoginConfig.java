package com.sample.fruitsellingweb.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserLoginConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails divyaUser = User.builder().
                username("Divya")
                .password(passwordEncoder().encode("divya")).roles("ADMIN").
                build();
        
        UserDetails johnUser = User.builder().
        		username("John")
        		.password(passwordEncoder().encode("john123")).roles("USER")
        		.build();
        
        UserDetails aliceUser = User.builder().
        		username("Alice")
        		.password(passwordEncoder().encode("alice123")).roles("USER")
        		.build();
        
        return new InMemoryUserDetailsManager(divyaUser, johnUser, aliceUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
