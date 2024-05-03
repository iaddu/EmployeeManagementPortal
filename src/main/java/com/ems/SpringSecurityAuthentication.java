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
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .csrf().disable() // Optionally disable CSRF if not using it
	            .authorizeRequests()
	                .antMatchers("/home", "/admin-login", "/employee-login").permitAll() // Permit access to these endpoints
	                .antMatchers("/admin/**").hasRole("ADMIN") // Secure /admin/** endpoints for ADMIN role
	                .antMatchers("/employee/**").hasRole("EMPLOYEE") // Secure /employee/** endpoints for EMPLOYEE role
	                .anyRequest().authenticated() // Require authentication for other endpoints
	            .and()
	            .formLogin()
	                .loginPage("/login") // Custom login page (shared or specific)
	                .defaultSuccessUrl("/home") // Redirect after successful login
	                .permitAll() // Permit access to the login page
	            .and()
	            .logout()
	                .permitAll(); // Permit access to logout
	    }
	 
}
