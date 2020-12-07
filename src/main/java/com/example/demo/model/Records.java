package com.example.demo.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "Records")
public class Records {

	@MongoId
	private String id;

	@Field("member_id")
	private long memberId;

	@Field("type")
	private String type;

	@Field("detial")
	private List<String> detail;

	@Field("count")
	private int count;

	@Field("score")
	private int score;

	@Field("target_member_id")
	private long targetMemberId;

	@Field("affected_members_id")
	private List<Long> affectedMembersId;
	
	@Field("created_time")
	private Date createdTime;
	
	public Records() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getDetail() {
		return detail;
	}

	public void setDetail(List<String> detail) {
		this.detail = detail;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public long getTargetMemberId() {
		return targetMemberId;
	}

	public void setTargetMemberId(long targetMemberId) {
		this.targetMemberId = targetMemberId;
	}

	public List<Long> getAffectedMembersId() {
		return affectedMembersId;
	}

	public void setAffectedMembersId(List<Long> affectedMembersId) {
		this.affectedMembersId = affectedMembersId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
}
