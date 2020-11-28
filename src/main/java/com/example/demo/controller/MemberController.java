package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.model.Records;
import com.example.demo.repository.IMemberRepository;
import com.example.demo.service.IMemberService;
import com.example.demo.service.impl.SequenceGeneratorService;


@RestController
@RequestMapping("/api/member")
public class MemberController {

	@Autowired
	private IMemberRepository userRepository; 
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	IMemberService memberService;
	
	@PostMapping("/")
	public ResponseEntity<Member> saveUser(@RequestBody Member member){
		Member newMember = memberService.saveMember(member);
		return ResponseEntity.ok(newMember);
	}
	
//	@GetMapping("/{name}")
//	public ResponseEntity<Member> findUser(@PathVariable("name")String name){
//		Query query = new Query();
//		System.out.println("name"+ name);
//		query.addCriteria(Criteria.where("name").is(name));
//		Member user = mongoTemplate.findOne(query, Member.class);
//		return ResponseEntity.ok(user);
//		
//	}
	
	@GetMapping("/findSome")
	public ResponseEntity<List<Member>> findUser(){
		Query query = new Query();
		final Pageable pageable = PageRequest.of(0, 1);
		query.with(pageable);
		return ResponseEntity.ok(mongoTemplate.find(query, Member.class));
		
	}
	
	@GetMapping("/{memberId}")
	public ResponseEntity<Member> findMemberById(@PathVariable("memberId")long memberId){
		Member member = userRepository.findById(memberId).orElse(null);
		return ResponseEntity.ok(member);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Member>> findAll(){
		return ResponseEntity.ok(userRepository.findAll());
	}
	
}
