package com.ems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
	public MyUserDetailService myUserDetailService;
	   @Bean
	    public CustomAuthenticationSuccessHandler authenticationSuccessHandler() {
	        return new CustomAuthenticationSuccessHandler(); // Return an instance of the custom success handler
	    }
	   /*
	@Bean
	public AuthenticationProvider authenticationprovider() {
		DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
		provider.setUserDetailsService(myUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	*/
	@Bean
	public UserDetailsService userDetailsService() {
		return myUserDetailService;
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
	    return httpSecurity
	        .csrf(csrf->csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	        		 .requestMatchers("/ems/**","/home/home.html").permitAll()
	 	            .requestMatchers("/admin/**").hasRole("ADMIN")
	               .requestMatchers("/manager/**").hasRole("MANAGER")
	 	           .requestMatchers("/emp/**").hasRole("EMPLOYEE")
	            .anyRequest().authenticated())
	        .formLogin(form -> form
	                .successHandler(authenticationSuccessHandler()) // Use the custom success handler
	                .permitAll())
	        .logout(logout -> logout
	                .logoutUrl("/logout") // Logout endpoint
	                .logoutSuccessUrl("/home") // Redirect to /home after logout
	                .permitAll()) // Allow public access to logout
	        .build();
	}
}
