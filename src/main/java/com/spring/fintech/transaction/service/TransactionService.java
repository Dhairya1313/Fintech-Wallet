package com.spring.fintech.transaction.service;

import org.springframework.data.domain.Page;

import com.spring.fintech.transaction.dto.TransactionDto;
import com.spring.fintech.transaction.entity.Transaction;
import com.spring.fintech.transaction.repository.TransactionRepository;

public interface TransactionService {
	
	public TransactionDto addTransaction(TransactionDto transactionDto, Integer senderWalletId, Integer receiverWalletId);
	public TransactionDto getTransactionById(Integer transactionId);
	public Page<TransactionDto> getWalletTransactions(Integer walletId, Integer page, Integer size);
}
