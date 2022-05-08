package com.jindo.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jindo.core.service.CustomerUserDetailService;

/**
 * Đặt Annotation Configuration để cho việc khi project run lên thì sẽ vào đây trước.
 * 
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomerUserDetailService customerUserDetailService;
	
	/**
	 * + hasAnyRole(null) -> muốn đi vào url này thì phải có một số ROLE nào đó
	 * + Trang custom login phải thế cho trang mặc định của /login Spring Security
	 * + Do trang login đã chuẩn các name: username | password nên không cần phải usernameParameter...
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/home", "/v1", "/v2", "/v3", "/v4").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/login?sucess=true")		// Nếu login thành công
				.failureUrl("/login?sucess=fail")				// Nếu login thất bại
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.and()
			.logout()
				.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//		auth.inMemoryAuthentication()
//		.withUser("manhkm").password(passwordEncoder().encode("manhkm"))
//		.authorities("ROLE_USER");
		auth.userDetailsService(customerUserDetailService)
			.passwordEncoder(passwordEncoder());
	}
	
	/**
	 * Sài kiểu mã hóa: BCrypt
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
