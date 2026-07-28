package com.spring.fintech.common.exception;

public class DuplicateEmailException extends RuntimeException{

	public DuplicateEmailException(String email) {
		super("Email already registered " +email);
	}
}
