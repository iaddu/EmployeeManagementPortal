package com.ems.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ems.model.User;

public class UserDaoTest {
	//	Optional<User> findUserByEmail(String email);
	
	private User user;
	
	@Mock
	private UserDao userDao;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		user=new User();
		user.setEmail("adi@gmail.com");
		user.setPassword("123");
	}
	
	
	@Test
	public void testFindUserByEmail() {
		when(userDao.findUserByEmail("adi@gmail.com")).thenReturn(Optional.of(user));
		Optional<User> result=userDao.findUserByEmail("adi@gmail.com");
		
		assertNotNull(result.get());
		assertEquals("adi@gmail.com", result.get().getEmail());
		assertEquals("123",result.get().getPassword());
		
	}
	

}
