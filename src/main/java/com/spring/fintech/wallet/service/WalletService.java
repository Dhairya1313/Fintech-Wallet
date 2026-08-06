package com.spring.fintech.wallet.service;

import com.spring.fintech.transaction.dto.TransactionResponseDto;
import com.spring.fintech.wallet.dto.WalletRequestDto;
import com.spring.fintech.wallet.dto.WalletResponseDto;

public interface WalletService {
	
	public WalletResponseDto addWallet(WalletRequestDto walletRequestDto);
	public double checkBalance(int walletId);
	public TransactionResponseDto addMoney(int walletId, double amount);
	public TransactionResponseDto transferMoney(int walletId, double amount, int receiverWalletId);
}
