package com.example.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.form.UpdateScoreForm;
import com.example.demo.model.Member;
import com.example.demo.model.Records;
import com.example.demo.model.TypeDetail;
import com.example.demo.service.IRecordService;

@Service
@Transactional
public class RecordServiceImpl implements IRecordService{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void saveRecords(List<Records> records) {
		
		for(Records record:records) {
			record.setCreatedTime(new Date());
			mongoTemplate.insert(record);
			UpdateScoreForm form = new UpdateScoreForm();
			form.setMemberId(record.getMemberId());
			form.setScore(record.getScore());
			this.updateScore(form);
		}
	}

	@Override
	public List<Records> findRecordsById(long memberId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("member_id").is(memberId));
		List<Records> records = mongoTemplate.find(query, Records.class);
		return records;
	}
	
	public void updateScore(UpdateScoreForm form) {
		Query query = new Query(Criteria.where("_id").is(form.getMemberId()));
		Update update = new Update();
		update.inc("score", form.getScore());
		mongoTemplate.updateFirst(query, update, Member.class);
	}

	@Override
	public long findCountByTypeName(long memberId,String type) {
	Query query = new Query(Criteria.where("type").is(type).and("member_id").is(memberId));
	return mongoTemplate.count(query, Records.class);
	}

	@Override
	public List<TypeDetail> findCountOfMainType(long memberId) {
		List<String> typeNames = Arrays.asList("胡","放槍","自摸","被自摸");
		return typeNames.stream()
				 .map(typeName ->getCountByTypename(memberId, typeName))
				 .collect(Collectors.toList());
	}
	
	public TypeDetail getCountByTypename(long memberId,String typeName){
		Query query = new Query(Criteria.where("member_id").is(memberId).and("type").is(typeName));
		TypeDetail detail = new TypeDetail();
		detail.setTypeName(typeName);
		detail.setOccuredTimes(mongoTemplate.count(query, Records.class));
		return detail;
	}

	@Override
	public List<Records> findCurrentMonthsOfRecords(long memberId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Query query = new Query(Criteria.where("created_time").gt(date).and("membner_id").is(memberId));
		return mongoTemplate.find(query, Records.class);
	}

}
