package com.spring.fintech.common.exception;

public class DuplicateUsernameException extends RuntimeException{
	
	public DuplicateUsernameException(String username) {
		super("Username already registered " +username);
	}
}
