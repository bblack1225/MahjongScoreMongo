package com.example.demo.form;


public class LoginForm {

	private String account;
	
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public LoginForm() {
		
	}

	public LoginForm(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}
	
	
}
