package com.team.art.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class ConfigUserWeb extends WebSecurityConfigurerAdapter {
	
	String[]PAGE_HOME= {"/home","/","/sginup","/login","/books"
			,"/viewEvent","/viewProduct/**","/products/**","/forgetPassword","/changePassword/**"};
	
	@Autowired
	private CustomUserService customUserService;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(customUserService);
		return provider;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth.authenticationProvider(authenticationProvider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  
		http.cors();
	   	http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(PAGE_HOME).permitAll()
		.antMatchers("/api/admin/**").hasRole("ADMIN")
		.antMatchers("/api/artist/**").hasRole("ARTIST")
		.antMatchers("/api/user/**").hasRole("USER")
		.anyRequest()
		.authenticated();
	   	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	   	.and()
	   	.exceptionHandling();
//	   	http.formLogin();
//	   	http.authenticationProvider(authenticationProvider());
	   	http.httpBasic();
	}
	
    @Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

}
