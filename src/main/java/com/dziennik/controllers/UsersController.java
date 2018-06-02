package com.dziennik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dziennik.domain.User;
import com.dziennik.exceptions.UserNotFoundException;
import com.dziennik.services.UserService;

@RestController
public class UsersController {

	@Autowired
	private UserService userService;

	@GetMapping("/student/{id}")
	public User getUserById(@PathVariable("id") int id) {
		User user = userService.findById(id);
		
		if(user == null) {
			throw new UserNotFoundException(id);
		}
		return user;
	}

	@PostMapping("/admin/addUser")
	@ResponseStatus(HttpStatus.CREATED)
	public void addUser(@RequestBody User user) {
		userService.create(user);
	}
	
	@GetMapping("/admin/deleteUser")
	public void deleteUser(@RequestBody User user){
		userService.delete(user);
	}
}
