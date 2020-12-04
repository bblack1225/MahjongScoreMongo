package com.example.demo.model;

import java.io.Serializable;

public class Account implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String accountType;
	private long id;
	private String sessionId;
	private Object accountDetails;
	
	
	
	public Account() {
	}
	
	public Account(String accountType, long id, String sessionId, Object accountDetails) {
		super();
		this.accountType = accountType;
		this.id = id;
		this.sessionId = sessionId;
		this.accountDetails = accountDetails;
	}

	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Object getAccountDetails() {
		return accountDetails;
	}
	public void setAccountDetails(Object accountDetails) {
		this.accountDetails = accountDetails;
	}
	
	
}
