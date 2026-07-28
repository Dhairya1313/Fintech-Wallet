package com.spring.fintech.common.exception;

public class InvalidTransactionException extends RuntimeException{
	public InvalidTransactionException(String message) {
		super(message);
	}

}
