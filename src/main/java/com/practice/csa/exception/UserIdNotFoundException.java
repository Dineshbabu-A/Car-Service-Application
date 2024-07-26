package com.practice.csa.exception;

public class UserIdNotFoundException extends RuntimeException{

	private String message;

	public UserIdNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
