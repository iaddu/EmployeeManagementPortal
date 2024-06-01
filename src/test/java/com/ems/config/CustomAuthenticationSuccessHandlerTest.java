package com.ems.config;

import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootTest
class CustomAuthenticationSuccessHandlerTest {

    @InjectMocks
    private CustomAuthenticationSuccessHandler successHandler;

    @MockBean
    private HttpServletRequest request;

    @MockBean
    private HttpServletResponse response; 
     
    @Test
    void testAuthenticationSuccessAdmin() throws IOException {
    	String uname="adi@gmail.com";
    	String upass="123";
        UserDetails userDetails = new User("uname", "upass", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));

        successHandler.onAuthenticationSuccess(request, response, new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, null));

        verify(response).sendRedirect("/admin/home");
    }

    @Test
    void testAuthenticationSuccessEmployee() throws IOException {
    	String uname="adi@gmail.com";
    	String upass="123";
        UserDetails userDetails = new User("uname", "upass", Collections.singleton(new SimpleGrantedAuthority("ROLE_EMPLOYEE")));

        successHandler.onAuthenticationSuccess(request, response, new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, null));

        verify(response).sendRedirect("/emp/home");
    }

    @Test
    void testAuthenticationSuccessManagerRole() throws IOException {
    	String uname="adi@gmail.com";
    	String upass="123";
        UserDetails userDetails = new User("uname", "upass", Collections.singleton(new SimpleGrantedAuthority("ROLE_MANAGER")));

        successHandler.onAuthenticationSuccess(request, response, new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, null));

        verify(response).sendRedirect("/manager/home");
    }

    @Test
    void testAuthenticationRedirectToBadCredentials() throws IOException {
        UserDetails userDetails = new User("unknown@example.com", "password", Collections.singleton(new SimpleGrantedAuthority("ROLE_UNKNOWN")));

        successHandler.onAuthenticationSuccess(request, response, new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, null));

        verify(response).sendRedirect("/bad-credentials");
    }
}
