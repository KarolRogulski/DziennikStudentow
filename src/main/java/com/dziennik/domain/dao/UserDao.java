package com.dziennik.domain.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dziennik.domain.User;

@Repository
public interface UserDao extends CrudRepository <User, Long> {

	public User findByNameAndSurname(String name, String surname);
	
	public User findByLogin(String login);
	
	public ArrayList<User> findAll();

	public User findById(int id);
}
