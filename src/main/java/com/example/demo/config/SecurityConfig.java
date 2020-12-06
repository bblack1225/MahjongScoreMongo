package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.auth.filter.LoginAuthenticationFilter;
import com.example.demo.auth.handler.LoginFaliureHandlerImpl;
import com.example.demo.auth.handler.LoginSuccessHandlerImpl;
import com.example.demo.auth.handler.LogoutSuccessHandlerImpl;
import com.example.demo.auth.provider.MemberAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(memberAuthenticationProvider());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAt(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).authorizeRequests()
		.antMatchers("/api/records/*").hasRole("MEMBER")
		.antMatchers("/api/member/*").hasRole("MEMBER")
		.and().logout().logoutUrl("/api/auth/logout").invalidateHttpSession(true).deleteCookies("JSESSINOID")
		.logoutSuccessHandler(logoutSuccessHandlerImpl()).and().csrf().disable();
	}
	
	@Bean
	LoginAuthenticationFilter loginAuthenticationFilter() throws Exception{
		LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationSuccessHandler(loginSuccessHandlerImpl());
		filter.setAuthenticationFailureHandler(loginFaliureHandlerImpl());
		filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/auth/login","POST"));
		return filter;
	}
	
	@Bean
	LoginSuccessHandlerImpl loginSuccessHandlerImpl() {
		return new LoginSuccessHandlerImpl();
	}
	
	@Bean
	LoginFaliureHandlerImpl loginFaliureHandlerImpl() {
		return new LoginFaliureHandlerImpl();
	}
	
	@Bean
	LogoutSuccessHandlerImpl logoutSuccessHandlerImpl() {
		return new LogoutSuccessHandlerImpl();
	}
	
	@Bean
	MemberAuthenticationProvider memberAuthenticationProvider() {
		return new MemberAuthenticationProvider();
	}
}
