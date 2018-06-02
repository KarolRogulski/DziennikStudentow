package com.dziennik.test;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dziennik.domain.Result;
import com.dziennik.domain.dao.ResultDao;
import com.dziennik.services.ResultService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResultServiceTest {

	@Autowired
	private ResultService resultService;

	@MockBean
	private ResultDao resultDao;

	Result result;

	@Before
	public void setUp() {
		result = new Result(4, "macko342");

		Mockito.when(resultDao.findById(result.getId())).thenReturn(result);
	}

	@Test
	public void findingResults() {
		Result foundById = resultService.findById(result.getId());

		assertSame(foundById.getValue(), result.getValue());
	}
}
