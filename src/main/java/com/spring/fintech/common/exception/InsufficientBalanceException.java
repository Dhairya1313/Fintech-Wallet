package com.spring.fintech.common.exception;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(Double balance, Double requested) {
        super("Insufficient balance. Available: " +balance+ ", Requested: "+requested);
    }
}
