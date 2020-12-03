package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Records;
import com.example.demo.service.IRecordService;

@RestController
@RequestMapping("/api/records")
public class RecordController {

	@Autowired
	private IRecordService recordService;
	
	@PostMapping("/saveRecords")
	public ResponseEntity<Object> saveRecords(@RequestBody List<Records> records){
		
		recordService.saveRecords(records);
		return ResponseEntity.status(HttpStatus.OK).body("save success");
	}
	
	@GetMapping("/{memberId}")
	public ResponseEntity<List<Records>> findRecordsById(@PathVariable("memberId") long memberId){

		List<Records> records = recordService.findRecordsById(memberId);
		return ResponseEntity.status(HttpStatus.OK).body(records);
	}
	
	@GetMapping("/typeCount/{memberId}/{type}")
	public ResponseEntity<Long> findTypeCountByTypeName(@PathVariable("memberId") long memberId,@PathVariable("type") String type){
		
		return ResponseEntity.status(HttpStatus.OK).body(recordService.findCountByTypeName(memberId, type));
	}
	
}
