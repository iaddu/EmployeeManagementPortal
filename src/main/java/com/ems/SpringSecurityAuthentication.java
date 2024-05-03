package com.ems;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SpringSecurityAuthentication {
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		User.UserBuilder user=User.withDefaultPasswordEncoder();
		UserDetails userOne=user
				.username("adi")
				.password("adi")
				.roles("USER")
				.build();
	return new InMemoryUserDetailsManager(userOne);
	} 
}
