package com.nelsonaguiar.testesoftplan;



//@Configuration
//@EnableWebSecurity
public class SecurityConfig/* extends WebSecurityConfigurerAdapter*/ {
 
//	 @Override
//	    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//	        auth.inMemoryAuthentication()
//	          .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
//	          .and()
//	          .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
//	          .and()
//	          .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
//	    }
//	 
//	    @Override
//	    protected void configure(final HttpSecurity http) throws Exception {
//	    	
//	    	 http.sessionManagement() .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	    	  .invalidSessionUrl("/index2.html");
//	    	
//	        http
//	          .csrf().disable()
//	          .authorizeRequests()
//	          .antMatchers("/admin/**").hasRole("ADMIN")
//	          .antMatchers("/anonymous*").anonymous()
//	          .antMatchers("/h2/**").permitAll()
//	          .antMatchers("/login*").permitAll()
//	          .anyRequest().authenticated()
//	          .and()
//	          .formLogin()
////	          .loginProcessingUrl("/perform_login")
//	          .defaultSuccessUrl("/index.html", true)
//	          //.failureUrl("/login.html?error=true")
//	          //.failureHandler(authenticationFailureHandler())
//	          .and()
//	          .logout()
//	          .logoutUrl("/perform_logout")
//	          .deleteCookies("JSESSIONID")
//	          .logoutSuccessHandler(logoutSuccessHandler())
//	          .logoutSuccessUrl("/index2.html");
//	        
//	       
//	    }
//	     
//	    private LogoutSuccessHandler logoutSuccessHandler() {
//	    	System.err.println("logout");
//			return null;
//		}
//
//		@Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
}