package com.spring.fintech.service;

public interface WalletService {
	
	public double checkBalance(int walletId);
	public double addMoney(int walletId, double amount);
	public double transferMoney(int walletId, double amount, int receiverWalletId);
}
