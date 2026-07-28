package com.spring.fintech.common.exception;

public class TransactionNotFoundException extends RuntimeException {

	public TransactionNotFoundException(Integer transactionId) {
        super("Transaction with ID "+transactionId+" not found.");
    }
}
