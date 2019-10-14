package com.nelsonaguiar.testesoftplan.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	 @Override
	    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication()
	          .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
	          .and()
	          .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("TESTE")
	          .and()
	          .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
	    }
	 
	    @Override
	    protected void configure(final HttpSecurity http) throws Exception {	    	

	         http
	          .csrf().disable()
	          .authorizeRequests()
	          .antMatchers("/source.html").permitAll()
	          .antMatchers("/h2/**").permitAll()
	          .and()
	          .authorizeRequests()
	          .anyRequest()
	          .authenticated()
	          .and()
	          .httpBasic()
	          .and()
	          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);    
	       
	    }

		@Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}