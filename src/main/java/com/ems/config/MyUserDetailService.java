package com.ems.config;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ems.dao.EmployeeDao;
import com.ems.dao.UserDao;
import com.ems.model.Employee;
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private EmployeeDao employeeDao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
    	Optional<com.ems.model.User> user = userDao.findUserByEmail(username);
        if (user.isPresent()) {
            com.ems.model.User userObj = user.get(); // Explicitly defining the type
            return User.builder()
                    .username(userObj.getEmail())
                    .password(userObj.getPassword())
                    .roles(userObj.getRole())
                    .build();
        } 
        
     // If user is not found in User table, search in Employee table
        Optional<com.ems.model.Employee> employee = employeeDao.findEmployeeByEmail(username);
        if (employee.isPresent()) {
            com.ems.model.Employee employeeObj = employee.get();
            // Construct UserDetails object for Employee
            System.out.println("hii emp");
            return User.builder() // Use Employee.builder() instead of User.builder()
                    .username(employeeObj.getEmail())
                    .password(employeeObj.getPassword())
                    .roles(employeeObj.getRole())
                    .build();
        } 
        
        else {
            throw new UsernameNotFoundException("no such user exists");
        }
        
        
    }
}
