package com.dziennik.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dziennik.domain.User;
import com.dziennik.domain.dao.UserDao;
import com.dziennik.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserDao userDao;
	
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp() {
		ArrayList<User> list = new ArrayList<>();
		user1 = new User("marek", "nowak", "email@gmail.com", "login", "12345");
		user2 = new User("wojtek", "kowalski", "email@gmail.com", "jakislogin", "12345");
		user3 = new User("kuba", "wojak", "email@gmail.com", "login", "12345");
		
		list.add(user1);
		list.add(user2);
		list.add(user3);
		
		Mockito.when(userDao.findByNameAndSurname(user1.getName(), user1.getSurname()))
			.thenReturn(user1);

		Mockito.when(userDao.findByLogin(user1.getLogin())).thenReturn(user1);
		
		Mockito.when(userDao.findAll()).thenReturn(list);
	}
	@Test
	public void findingUser() {
		User foundByLogin = userService.findByLogin(user1.getLogin());
		User foundByNameAndSurname = userService.findByNameAndSurname(user1.getName(), user1.getSurname());
		ArrayList<User> list = userService.findAll();
		
		assertTrue(foundByLogin.getLogin().equals(user1.getLogin()));
		assertTrue(foundByNameAndSurname.getId() == user1.getId());
		assertTrue(list.size() == 3);
	}
	
	@Test
	public void createUser() {
		userService.create(user1);
		User user1 = userService.findByLogin("login");
		
		assertTrue(user1.getLogin().equals("login"));
		assertFalse(user1.getRoles().isEmpty());
	}
}
