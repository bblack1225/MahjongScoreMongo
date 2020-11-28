package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Member;

	
public interface IMemberRepository extends MongoRepository<Member, Long>{
	
	List<Member> findAll();
	
}
