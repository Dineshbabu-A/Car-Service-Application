package com.practice.csa.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.practice.csa.exception.CarIdNotFoundException;
import com.practice.csa.exception.CarServiceIdNotFoundException;
import com.practice.csa.exception.UserIdNotFoundException;

@RestControllerAdvice
public class ApplicationHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> carIdNotFoundException(CarIdNotFoundException carIdNotFoundException){
		
		ErrorStructure<String> errorStructure=new ErrorStructure<String>();
		
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(carIdNotFoundException.getMessage());
		errorStructure.setData("Provide valid Service Id ");
		
		return new ResponseEntity<ErrorStructure<String>>(errorStructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> carServiceIdNotFoundException(CarServiceIdNotFoundException carServiceIdNotFoundException){
		
		ErrorStructure<String> errorStructure=new ErrorStructure<String>();
		
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(carServiceIdNotFoundException.getMessage());
		errorStructure.setData("Provide valid Service Id ");
		
		return new ResponseEntity<ErrorStructure<String>>(errorStructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> userIdNotFoundException(UserIdNotFoundException userIdNotFoundException){
		
		ErrorStructure<String> errorStructure=new ErrorStructure<String>();
		
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(userIdNotFoundException.getMessage());
		errorStructure.setData("Provide valid Service Id ");
		
		return new ResponseEntity<ErrorStructure<String>>(errorStructure,HttpStatus.NOT_FOUND);
	}
}
