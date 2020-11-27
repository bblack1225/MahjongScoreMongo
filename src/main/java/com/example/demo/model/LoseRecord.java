package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class LoseRecord {
	
	@Field("type")
	private String loseType;
	
	@Field("lose_count")
	private int loseCount;
	
	@Field("lose_score")
	private int loseScore;
	
	@Field("lose_to_member_id")
	private String loseTo;

	public String getLoseType() {
		return loseType;
	}

	public void setLoseType(String loseType) {
		this.loseType = loseType;
	}

	public int getLoseCount() {
		return loseCount;
	}

	public void setLoseCount(int loseCount) {
		this.loseCount = loseCount;
	}

	public int getLoseScore() {
		return loseScore;
	}

	public void setLoseScore(int loseScore) {
		this.loseScore = loseScore;
	}

	public String getLoseTo() {
		return loseTo;
	}

	public void setLoseTo(String loseTo) {
		this.loseTo = loseTo;
	}
	
	public LoseRecord() {
		
	}
}
