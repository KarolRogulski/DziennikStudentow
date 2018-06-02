package com.dziennik.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dziennik.domain.User;
import com.dziennik.domain.dao.UserDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class UserDaoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserDao userDao;

	@Test
	public void findAndReturnStudent() {
		User user1 = new User("marek", "nowak", "email@gmail.com", "login", "12345");
		User user2 = new User("wojtek", "kowalski", "email@gmail.com", "jakislogin", "12345");
		User user3 = new User("kuba", "wojak", "email@gmail.com", "wymyslnyLogin", "12345");
		entityManager.persist(user1);
		entityManager.persist(user2);
		entityManager.persist(user3);
		entityManager.flush();
		
		User foundByNameAndSurname = userDao.findByNameAndSurname("marek", "nowak");
		User foundByLogin = userDao.findByLogin(user1.getLogin());
		ArrayList<User> list = userDao.findAll();
				
		assertTrue(foundByNameAndSurname.getName().equals("marek"));
		assertTrue(foundByLogin.getLogin().equals(user1.getLogin()));
		assertTrue(list.size()==3);
	}
}
