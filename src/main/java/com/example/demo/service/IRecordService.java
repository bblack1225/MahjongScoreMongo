package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Records;
import com.example.demo.model.TypeDetail;

public interface IRecordService {

	void saveRecords(List<Records> records);
	
	List<Records> findRecordsById(long memberId);
	
	long findCountByTypeName(long memberId,String type);
	
	List<TypeDetail> findCountOfMainType(long memberId);
	
	List<Records> findCurrentMonthsOfRecords(long memberId);
}
