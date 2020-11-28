package com.example.demo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "Teams")
public class Team {
	
	@Transient
	public static final String SEQUENCE_NAME = "team_sequence";
	
	@Id
	private long id;
	
	private String account;
	
	private String password;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastPlayed;
	
	public Team() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastPlayed() {
		return lastPlayed;
	}

	public void setLastPlayed(Date lastPlayed) {
		this.lastPlayed = lastPlayed;
	}
	
}
