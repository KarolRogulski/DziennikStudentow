package com.dziennik.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dziennik.domain.Result;
import com.dziennik.domain.dao.ResultDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class ResultDaoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ResultDao resultDao;

	private Result result1;

	@Before
	public void setUp() {
		result1 = new Result(4, "macko623");

		entityManager.persist(result1);
		entityManager.flush();
	}

	@Test
	public void findAndReturnGrade() {
		Result foundById = resultDao.findById(result1.getId());

		assertSame(foundById.getValue(), result1.getValue());
	}

	@Test
	public void wrongInput() {
		assertNull(resultDao.findById(20));
	}
}
