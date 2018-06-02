package com.dziennik.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dziennik.domain.Role;
import com.dziennik.domain.User;
import com.dziennik.domain.dao.UserDao;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private final static Logger userServiceLogger = LoggerFactory.getLogger(com.dziennik.services.UserService.class);
	
	
	public User findByNameAndSurname(String name, String surname) {
		User user = userDao.findByNameAndSurname(name, surname);
		userServiceLogger.info(String.format("Returning user with name: %s surname: %s", name, surname));
		return user;
	}

	public ArrayList<User> findAll() {
		userServiceLogger.info("Returning all students");
		return userDao.findAll();
	}

	public User create(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = new Role();
		
		if(user.isTeacher()){
			userRole.setRole("ROLE_TEACHER");
			userServiceLogger.info("Creating teacher: " + user.toString());
		}
		
		else{
		userRole.setRole("ROLE_STUDENT");
		userServiceLogger.info("Creating student: " + user.toString());
		}
		
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return userDao.save(user);
	}
// ----0 / 1
	public void delete(User user) {
		userServiceLogger.info("Deleting user: " + user.toString());
		userDao.delete(user);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = userDao.findByLogin(login);
		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
		return buildUserForAuthentication(user, authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Role role : userRoles) {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles);
		return grantedAuthorities;
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
				authorities);
	}

	public User findByLogin(String login) {
		userServiceLogger.info("Returning user with login: " + login);
		return userDao.findByLogin(login);
	}

	public User findById(int id) {
		User user = userDao.findById(id);
		return user;
	}
}