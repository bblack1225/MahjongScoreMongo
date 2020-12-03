package com.example.demo.service;

import java.util.List;

import com.example.demo.form.LoginForm;
import com.example.demo.model.Member;
import com.example.demo.model.Team;

public interface ITeamService {

	Team saveTeam(Team team);
	
	Team login(LoginForm form);
	
	List<Member> findMembersByTeamId(long teamId);
}
