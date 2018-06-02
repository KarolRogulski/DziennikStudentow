package com.dziennik.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Result")
public class ResultNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6002842532301165486L;
	private String errorMessage = "Couldn't find result with ";

	public ResultNotFoundException(int id) {
		errorMessage = errorMessage + "id: " + id;
	}

	public ResultNotFoundException(String name, String surname) {
		errorMessage = errorMessage + "name: " + name + "surname: " + surname;
	}

	@Override
	public String toString() {
		return errorMessage;
	}
}