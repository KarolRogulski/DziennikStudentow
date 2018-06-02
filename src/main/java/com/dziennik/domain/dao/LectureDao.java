package com.dziennik.domain.dao;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dziennik.domain.Lecture;

@Repository
public interface LectureDao extends CrudRepository<Lecture, Long>{
	
	public Lecture findById(int id);
	
	public Lecture findByDate(LocalDate date);

	public Set<Lecture> findAll();
}
