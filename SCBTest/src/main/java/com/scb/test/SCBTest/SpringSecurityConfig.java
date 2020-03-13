package com.scb.test.SCBTest;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private DataSource dataSource;
    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {        
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
		.usersByUsernameQuery("select username,password, enabled  from user_profile where username=?")
        .authoritiesByUsernameQuery("select username, role from user_roles where username=?");
    }
    
  //Authorization
  	@Override
  	protected void configure(HttpSecurity http) throws Exception{
  		http
        .authorizeRequests()
  		.antMatchers(HttpMethod.POST,"/users/login").permitAll()
  		.antMatchers(HttpMethod.POST,"/users").permitAll()
  		.antMatchers("/books").permitAll()	
  		.and()
  		.httpBasic()
  		.and()
 		.csrf().disable();
  	}
  	
  	
}
