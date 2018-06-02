package com.dziennik.domain.dao;

import org.springframework.data.repository.CrudRepository;

import com.dziennik.domain.Role;

public interface RoleDao extends CrudRepository<Role, Integer>{
	public Role findByRole(String role);

}
