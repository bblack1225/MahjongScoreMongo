package com.example.demo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Member;
import com.example.demo.repository.IMemberRepository;
import com.example.demo.service.IMemberService;

@Service
@Transactional
public class MemberServiceImpl implements IMemberService{

	@Autowired
	private SequenceGeneratorService sequenceService;
	
	@Autowired
	private IMemberRepository memberRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public Member saveMember(Member member) {
		member.setCreatedDate(new Date());
		member.setId(sequenceService.generateSequence(Member.SEQUENCE_NAME));
		memberRepository.save(member);
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(member.getId()));
		return memberRepository.findById(member.getId()).get();
	}
	

}
