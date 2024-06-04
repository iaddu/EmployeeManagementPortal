package com.ems.config;
import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         Collection<? extends SimpleGrantedAuthority> authorities = (Collection<? extends SimpleGrantedAuthority>) userDetails.getAuthorities();
         if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            response.sendRedirect("/admin/home");
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_EMPLOYEE"))) {
            response.sendRedirect("/emp/home");
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            response.sendRedirect("/manager/home");
        } else {
        	System.out.println("helelo");
            response.sendRedirect("/bad-credentials");
        }
    }
}

