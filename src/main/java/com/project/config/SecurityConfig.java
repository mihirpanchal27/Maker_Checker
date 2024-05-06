package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailService;
	
	protected void configure(HttpSecurity http) throws Exception{
		http
				.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/maker/validate").permitAll()
				.antMatchers("/get-login").authenticated()
				.antMatchers("/maker/**").hasRole("MAKER")
				.antMatchers("/checker/**").hasRole("CHECKER")
				.and()
				.formLogin()
				.permitAll()
				.and()
				.logout()
				.logoutSuccessUrl("/");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}
}
