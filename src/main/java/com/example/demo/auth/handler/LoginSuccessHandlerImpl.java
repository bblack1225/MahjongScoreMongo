package com.example.demo.auth.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpStatus.OK.value());

		PrintWriter out = response.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		Account account = (Account) authentication.getDetails();
		out.write(objectMapper.writeValueAsString(account.getAccountDetails()));
		out.flush();
		out.close();
	}

}
