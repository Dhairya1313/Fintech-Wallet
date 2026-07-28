package com.spring.fintech.common.exception;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(Integer userId) {
		super("User with ID "+ userId +" not found.");
	}
	
	public UserNotFoundException(String userName) {
		super("User with ID "+ userName +" not found.");
	}
}
