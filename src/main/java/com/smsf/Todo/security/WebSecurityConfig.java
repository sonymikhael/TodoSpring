package com.smsf.Todo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BasicAuthenticationEntryPoint authenticationEntryPoint;
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/user/register").permitAll()
			.and()
			.authorizeRequests()
			.antMatchers("/h2-console/**").permitAll() // for h2 console access during dev only
			.anyRequest().authenticated()
			.and()
			.httpBasic()
			.authenticationEntryPoint(authenticationEntryPoint)
			.and()
			.csrf().disable(); // this is purely api based, no browser access
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // don't set cookies
		http.headers().frameOptions().sameOrigin(); // for h2 console frames
		http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
	}
}
