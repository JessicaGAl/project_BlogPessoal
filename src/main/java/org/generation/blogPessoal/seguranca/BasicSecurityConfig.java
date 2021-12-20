package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
	private UserDetailsService userDetailsService;
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
			auth.inMemoryAuthentication().withUser("JessGomes").password(passwaordEncoder().encode("041993"))
					.authorities("ROLE_ADMIN");
		}
		
	@Bean 
	public PasswordEncoder passwaordEncoder () {
		return new BCryptPasswordEncoder();
	}
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()                                        // autorizar a rota que vai passar
		.antMatchers("/usuarios/logar.").permitAll()    //permissão para rota qualquer um = All  
		.antMatchers("/usuarios/cadastrar").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()  // resposta HTTP
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // entrada por sessão
		.and().cors()     // 
		.and().csrf().disable();  
	}

}
