package com.dziennik.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Lecture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	private LocalDate date;
	
	@NotEmpty
	private String subject;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<User> presence = new HashSet<>();
	
	protected Lecture() {}

	public Lecture(LocalDate date, String subject) {
		this.date = date;
		this.subject = subject;
	}
	
	public void addPresence(User user) {
		presence.add(user);
	}
	
	public void addPresence(Set<User> users) {
		presence.addAll(users);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void addPresentStudent(User student) {
		presence.add(student);
	}

	public Set<User> getPresence() throws NullPointerException {
		return presence;
	}

	public void setPresence(HashSet<User> presence) {
		this.presence = presence;
	}
}
