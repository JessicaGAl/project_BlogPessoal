package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity

public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{ 

	@Autowired
	private UserDetailsService service;
  
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(service);

		auth.inMemoryAuthentication().withUser("jess").password(passwordEncoder().encode("jessboaz"))
				.authorities("ROLE_ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/usuarios/cadastrar").permitAll()
				.antMatchers(HttpMethod.POST, "/usuarios/logar").permitAll()
				.antMatchers(HttpMethod.GET, "/usuarios/{id}").permitAll()
				.antMatchers(HttpMethod.PUT, "/usuarios/atualizar").permitAll()
				.antMatchers(HttpMethod.GET, "/temas").permitAll()
				.antMatchers(HttpMethod.POST, "/temas").permitAll()
				.antMatchers(HttpMethod.PUT, "/temas").permitAll()
				.antMatchers(HttpMethod.GET, "/temas/{id}").permitAll()
				.antMatchers(HttpMethod.DELETE, "/temas/{id}").permitAll()
				.antMatchers(HttpMethod.GET, "/postagens").permitAll()
				.antMatchers(HttpMethod.POST, "/postagens").permitAll()
				.antMatchers(HttpMethod.PUT, "/postagens").permitAll()
				.antMatchers(HttpMethod.GET, "/postagens/{id}").permitAll()
				.antMatchers(HttpMethod.DELETE, "/postagens/{id}").permitAll()
				.antMatchers(HttpMethod.GET, "/postagens/titulo").permitAll()
				.antMatchers(HttpMethod.GET, "/postagens/titulo/{titulo}").permitAll()
				.anyRequest().authenticated()
			.and().httpBasic()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().cors()
			.and().csrf().disable();
	}

}