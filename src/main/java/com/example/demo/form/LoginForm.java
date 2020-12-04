package com.example.demo.form;


public class LoginForm {

	private String email;
	
	private String password;
	
	private String sessionId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public LoginForm() {
		
	}

	public LoginForm(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public LoginForm(String email, String password, String sessionId) {
		super();
		this.email = email;
		this.password = password;
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
}
