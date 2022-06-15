package br.com.alura.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
        .authorizeRequests()
            .antMatchers(
                    "/bootstrap/**", "/home/**", "/api/**").permitAll() 
            .anyRequest().authenticated()
            .and()
        .formLogin(form -> form 
            .loginPage("/login")
            .defaultSuccessUrl("/usuario/pedido", true)
            .permitAll()).logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/home"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		//criar um user
		//UserDetails user = User.builder().username("couto").password(encoder.encode("couto")).roles("ADM").build();
		
		auth.jdbcAuthentication()
		.dataSource(dataSource).passwordEncoder(encoder); // criar um user .withUser(user)
	}
	
//	@Bean
	//@Override
	//public UserDetailsService userDetailsService() {
		
	//	UserDetails user = User.withDefaultPasswordEncoder().username("couto").password("couto").roles("ADM").build();
	
		
		//return new InMemoryUserDetailsManager(user);
//	}
	
	
}
