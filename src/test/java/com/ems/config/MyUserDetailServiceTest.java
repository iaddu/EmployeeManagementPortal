package com.ems.config;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ems.dao.EmployeeDao;
import com.ems.dao.UserDao;
import com.ems.model.Employee;
import com.ems.model.User;

class MyUserDetailServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private MyUserDetailService myUserDetailService;
    
    private User user;
    
    private Employee employee;
    
    @BeforeEach
    public void setUp() {
    	MockitoAnnotations.openMocks(this);
    	user =new User();
    	employee =new Employee();
    	String username = "adi@123";
        user.setEmail(username);
        user.setPassword("123");
        user.setRole("ADMIN");
        
        employee.setEmail(username);
        employee.setPassword("123");
        employee.setRole("EMPLOYEE");
    }

    @Test
    void testLoadUserByUsernameUserExists() {
    	String username = "adi@123";
        when(userDao.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        UserDetails userDetails = myUserDetailService.loadUserByUsername(user.getEmail());
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("123", userDetails.getPassword());
    }

    @Test
    void testLoadUserByUsernameEmployeeExists() {
    	String username = "adi@123"; 
        when(userDao.findUserByEmail(username)).thenReturn(Optional.empty());
        when(employeeDao.findEmployeeByEmail(username)).thenReturn(Optional.of(employee));
        UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("123", userDetails.getPassword());
    }

    @Test
    void testLoadUserByUsernameUserAndEmployeeNotExist() {
        String username = "adi@gmail.com";
        when(userDao.findUserByEmail(username)).thenReturn(Optional.empty());
        when(employeeDao.findEmployeeByEmail(username)).thenReturn(Optional.empty());
        
        assertThrows(UsernameNotFoundException.class, () -> myUserDetailService.loadUserByUsername(username));
    }
}

