package com.spring.fintech.transaction.service;

import org.springframework.data.domain.Page;

import com.spring.fintech.transaction.dto.TransactionRequestDto;
import com.spring.fintech.transaction.dto.TransactionResponseDto;

public interface TransactionService {
	
	public TransactionResponseDto addTransaction(TransactionRequestDto transactionDto, Integer senderWalletId, Integer receiverWalletId);
	public TransactionResponseDto getTransactionById(Integer transactionId);
	public Page<TransactionResponseDto> getWalletTransactions(Integer walletId, Integer page, Integer size);
}
