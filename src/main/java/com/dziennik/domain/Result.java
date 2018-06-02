package com.dziennik.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="result")
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Min(2)
	@Max(5)
	@Column(name="value")
	private int value;

	@NotEmpty
	@Column(name="student_login")
	private String studentLogin;

	protected Result() {}
	
	public Result(int value, String studentLogin) {
		this.value = value;
		this.studentLogin = studentLogin;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getStudentLogin() {
		return studentLogin;
	}

	public void setStudentLogin(String studentLogin) {
		this.studentLogin = studentLogin;
	}

	public int getId() {
		return id;
	}
}
