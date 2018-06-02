package com.dziennik.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Student")
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3606060142141886170L;

	private final static Logger userExceptionLogger = LoggerFactory.getLogger(com.dziennik.exceptions.UserNotFoundException.class);
	
	private String errorMessage = "Couldn't find userwith ";
	
	public UserNotFoundException(String login) {
		userExceptionLogger.error("user with login: " + login + " not found");
		errorMessage = errorMessage + "login: " + login;
	}
	
	public UserNotFoundException(String name, String surname) {
		userExceptionLogger.error("User with name: " + name + " surname " + surname + " not found");
		errorMessage = errorMessage + "name: " + name + "surname: " + surname; 
	}
	
	public UserNotFoundException(int id) {
		userExceptionLogger.error("User with id: " + id);
		errorMessage = errorMessage + "login: " + id;
	}

	@Override
	public String toString() {
		return errorMessage;
	}
}
