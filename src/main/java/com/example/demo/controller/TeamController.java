package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.form.LoginForm;
import com.example.demo.model.Team;
import com.example.demo.service.ITeamService;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

	@Autowired
	private ITeamService teamService;
	
	@PostMapping("/")
	public ResponseEntity<Team> saveTeam(@RequestBody Team team){
		Team newTeam = teamService.saveTeam(team);
		return ResponseEntity.status(HttpStatus.OK).body(newTeam);
	}
	
	@GetMapping("/")
	public ResponseEntity<Team> getTeam(@RequestBody String email){
		Team team = teamService.findByEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body(team);
	}

}
