package com.dziennik.domain.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dziennik.domain.Result;

@Repository
public interface ResultDao extends CrudRepository<Result, Long> {

	Result findById(int id);
	
	Result findByStudentLogin(String studentLogin);
}
