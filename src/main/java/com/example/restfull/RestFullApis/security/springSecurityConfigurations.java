package com.example.restfull.RestFullApis.security;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class springSecurityConfigurations {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//step 1
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
		
		//step 2
		http.httpBasic(withDefaults());
		
		//step 3
		http.csrf().disable();		
		
		
		
		return http.build();
	}
}
