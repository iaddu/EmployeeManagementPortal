package com.ems.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityConfigTest {
	@Mock
	private HttpSecurity httpSecurity;
	
	@InjectMocks
	private SecurityConfig securityConfig;
	
	@Mock
	private MyUserDetailService userDetialService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	

    @Test
    public void testPasswordEncoderBeanCreation() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
    }

    @Test
    public void testAuthenticationProviderBeanCreation() {
        AuthenticationProvider authenticationProvider = securityConfig.authenticationprovider();
        assertNotNull(authenticationProvider);
    }

    @Test
    public void testUserDetailsServiceBeanCreation() {
        UserDetailsService userDetailsService = securityConfig.userDetailsService();
        assertNotNull(userDetailsService);
    }

     
}
