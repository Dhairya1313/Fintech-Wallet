package com.spring.fintech.service;

import org.springframework.data.domain.Page;

import com.spring.fintech.entity.Transaction;
import com.spring.fintech.entity.dto.TransactionDto;
import com.spring.fintech.repository.TransactionRepository;

public interface TransactionService {
	
	public TransactionDto addTransaction(TransactionDto transactionDto, Integer senderWalletId, Integer receiverWalletId);
	public TransactionDto getTransactionById(Integer transactionId);
	public Page<TransactionDto> getWalletTransactions(Integer walletId, Integer page, Integer size);
}
