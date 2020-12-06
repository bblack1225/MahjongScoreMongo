package com.example.demo.auth.filter;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.example.demo.auth.token.MemberAuthenticationToken;
import com.example.demo.form.LoginForm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public LoginAuthenticationFilter() {
		super(new AntPathRequestMatcher("/login", "POST"));
	}
	
	protected LoginAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}

	private static String ACCOUNT_TYPE = "Account-Type";
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		String accountType = request.getHeader(ACCOUNT_TYPE);
		
		if(accountType == null || accountType.isEmpty()) {
			throw new BadCredentialsException("accountType is null or empty");
		}
		
		String requestBody;
		requestBody = request.getReader().lines().collect(Collectors.joining());
		LoginForm loginForm = objectMapper.readValue(requestBody, LoginForm.class);
		
		Authentication token;
		if(accountType.equals("MEMBER")) {
			token = new MemberAuthenticationToken(loginForm.getEmail(),loginForm.getPassword(), loginForm.getSessionId());
		}else {
			throw new BadCredentialsException("account type not found!");
		}
		
		return this.getAuthenticationManager().authenticate(token);
	}
	
	

}
