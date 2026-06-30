package com.spring.fintech.wallet.service;

import com.spring.fintech.transaction.dto.TransactionDto;
import com.spring.fintech.wallet.dto.WalletDto;

public interface WalletService {
	
	public WalletDto addWallet(WalletDto walletDto);
	public double checkBalance(int walletId);
	public TransactionDto addMoney(int walletId, double amount);
	public TransactionDto transferMoney(int walletId, double amount, int receiverWalletId);
}
