package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Records;

public interface IRecordService {

	void saveRecords(List<Records> records);
	
	List<Records> findRecordsById(long memberId);
}
