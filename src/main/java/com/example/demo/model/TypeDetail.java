package com.example.demo.model;

public class TypeDetail {

	private String typeName;
	
	private long occuredTimes;

	

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public long getOccuredTimes() {
		return occuredTimes;
	}

	public void setOccuredTimes(long occuredTimes) {
		this.occuredTimes = occuredTimes;
	}
	
	public TypeDetail(){
		
	}

	public TypeDetail(String typeName, long occuredTimes) {
		super();
		this.typeName = typeName;
		this.occuredTimes = occuredTimes;
	}

}
