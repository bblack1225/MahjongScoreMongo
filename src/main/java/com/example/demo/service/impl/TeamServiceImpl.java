package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.form.LoginForm;
import com.example.demo.model.Member;
import com.example.demo.model.Team;
import com.example.demo.repository.ITeamRepository;
import com.example.demo.service.ITeamService;

@Service
@Transactional
public class TeamServiceImpl implements ITeamService {

	@Autowired
	ITeamRepository teamRepository;
	
	@Autowired
	SequenceGeneratorService sequenceService;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Team saveTeam(Team team) {
		team.setCreatedTime(new Date());
		team.setId(sequenceService.generateSequence(Team.SEQUENCE_NAME));
		team.setPassword(passwordEncoder.encode(team.getPassword()));
		teamRepository.save(team);
		return teamRepository.findById(team.getId()).get();
	}

//	@Override
//	public Team login(LoginForm form) {
//		Query query = new Query(Criteria.where("account").is(form.getAccount()).and("password").is(form.getPassword()));
//		Team team = mongoTemplate.findOne(query, Team.class);
//			return team;
//	}

	@Override
	public List<Member> findMembersByTeamId(long teamId) {
		Query query = new Query(Criteria.where("team_id").is(teamId));
		return mongoTemplate.find(query, Member.class);
	}

	@Override
	public Team findByEmail(String email) {
		Query query = new Query(Criteria.where("account").is(email));
		return mongoTemplate.findOne(query, Team.class);
	}
	
	

}
