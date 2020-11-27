package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.Records;
import com.example.demo.service.IRecordService;

@Service
public class RecordServiceImpl implements IRecordService{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void saveRecords(List<Records> records) {
		
		for(Records record:records) {
			mongoTemplate.insert(record);
		}
	}

	@Override
	public List<Records> findRecordsById(long memberId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("member_id").is(memberId));
		List<Records> records = mongoTemplate.find(query, Records.class);
		return records;
	}

}
