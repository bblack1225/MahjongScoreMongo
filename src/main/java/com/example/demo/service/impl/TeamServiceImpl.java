package com.example.demo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Override
	public Team saveTeam(Team team) {
		team.setCreatedTime(new Date());
		team.setId(sequenceService.generateSequence(Team.SEQUENCE_NAME));
		teamRepository.save(team);
		return teamRepository.findById(team.getId()).get();
	}

}
