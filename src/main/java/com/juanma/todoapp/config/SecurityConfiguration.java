package com.juanma.todoapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.juanma.todoapp.services.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public UsuarioService usuarioService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/css/**", "/js/**", "/img/**", "/", "/sing-up").permitAll()
			.antMatchers("/**").authenticated()
			.and().formLogin().loginPage("/").loginProcessingUrl("/auth")
			.usernameParameter("username").passwordParameter("password")
			.defaultSuccessUrl("/panel").failureUrl("/?err=login%20error").permitAll()
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
			.and().csrf()
				.disable();
	}
}
