package co.simplon.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@EnableWebSecurity
public class InMemoryWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	
    
	@Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("simplon").roles("USER")
			.and()
			.withUser("developper").password("simplon").roles("DEVELOPPER")
			.and()
			.withUser("manager").password("{noop}simplon").roles("MANAGER")
			.and()
			.withUser("admin").password("{bcrypt}$2a$10$OhwFVfhBW0Rv2TUtS4UFSOtvMFbGnPPEFkFcKnXif9bBAfWFnKm16").roles("ADMIN");
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/").permitAll()
        	.antMatchers("/public").permitAll()
        	.antMatchers("/deny").denyAll()
        	.antMatchers("/developper").hasAnyRole("DEVELOPPER", "ADMIN")
        	.antMatchers("/manager").hasAnyRole("MANAGER", "ADMIN")
        	.antMatchers("/admin").hasAnyRole("ADMIN")
        	.anyRequest().authenticated()
        	.and()
        	.formLogin().permitAll()
        	.and()
        	.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		System.out.println(bcrypt.encode("simplon"));
	}
}
