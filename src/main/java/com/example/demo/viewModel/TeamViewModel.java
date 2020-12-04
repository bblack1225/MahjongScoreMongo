package com.example.demo.viewModel;

import java.util.Date;
import java.util.List;

import com.example.demo.model.Member;

public class TeamViewModel {
	
	private long teamId;
	
	private String email;
	
	private String teamName;
	
	private List<Member> players;

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<Member> getMembersOfTeam() {
		return players;
	}

	public void setMembersOfTeam(List<Member> players) {
		this.players = players;
	}

	public TeamViewModel() {
	}
	
}
