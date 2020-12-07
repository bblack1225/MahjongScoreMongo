package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleException(Exception exception){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
