package com.example.demo.auth.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpStatus.OK.value());

		PrintWriter out = response.getWriter();
		Map<String, String> result = new HashMap<>();
		result.put("message", "登出成功");
		ObjectMapper ObjectMapper = new ObjectMapper();
		out.write(ObjectMapper.writeValueAsString(result));
		out.flush();
		out.close();

	}

}
