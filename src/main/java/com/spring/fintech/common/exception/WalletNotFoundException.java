package com.spring.fintech.common.exception;

public class WalletNotFoundException extends RuntimeException{
	
	public WalletNotFoundException(Integer walletId) {
		super("Wallet with ID "+walletId+" not found");
	}
}
