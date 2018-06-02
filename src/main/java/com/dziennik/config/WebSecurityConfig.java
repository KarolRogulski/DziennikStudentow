package com.dziennik.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	            .antMatchers("/").permitAll()
	            .antMatchers("/**").hasAuthority("ROLE_ADMIN")
	            .antMatchers("/teacher/**", "/student**").hasAuthority("ROLE_TEACHER")
	            .antMatchers("/student/**").hasAuthority("ROLE_STUDENT")
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .loginPage("/login")
	            .permitAll()
	            .and()
	        .logout()
	            .permitAll();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth
	        .userDetailsService(userDetailsService)
	        .passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
 	public void configure(WebSecurity web) throws Exception {
 	    web
 	       .ignoring()
 	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
 	}
 	
 	@Bean
 	public BCryptPasswordEncoder passwordEncoder() {
 		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
 		return bCryptPasswordEncoder;
 	}
}