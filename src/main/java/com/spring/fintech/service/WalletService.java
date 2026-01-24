package com.spring.fintech.service;

import com.spring.fintech.entity.dto.TransactionDto;
import com.spring.fintech.entity.dto.WalletDto;

public interface WalletService {
	
	public WalletDto addWallet(WalletDto walletDto);
	public double checkBalance(int walletId);
	public TransactionDto addMoney(int walletId, double amount);
	public TransactionDto transferMoney(int walletId, double amount, int receiverWalletId);
}
