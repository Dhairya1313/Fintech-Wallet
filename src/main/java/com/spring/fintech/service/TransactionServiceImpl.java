package com.spring.fintech.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.fintech.entity.Transaction;
import com.spring.fintech.entity.dto.TransactionDto;
import com.spring.fintech.repository.TransactionRepository;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService{

	private TransactionRepository transactionRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository, ModelMapper modelMapper) {
		super();
		this.transactionRepository = transactionRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public TransactionDto addTransaction(TransactionDto transactionDto, Integer senderWalletId,
			Integer receiverWalletId) {
		
		Transaction transaction = modelMapper
				.map(transactionDto, Transaction.class);
		
		transaction.setSenderWalletId(senderWalletId);
		transaction.setReceiverWalletId(receiverWalletId);
		
		Transaction saved = transactionRepository.save(transaction);
		
		return modelMapper.map(saved, TransactionDto.class);
	}

	@Override
	public TransactionDto getTransactionById(Integer transactionId) {
		
		Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(()-> new RuntimeException("Transaction not found"));
	
		return modelMapper.map(transaction, TransactionDto.class);
	}

	@Override
	public Page<TransactionDto> getWalletTransactions(Integer walletId, Integer page, Integer size) {
		
		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
		return transactionRepository.findTransactionsByWalletId(walletId,pageable)
				.map(transaction-> modelMapper.map(transaction, TransactionDto.class));
	}

}
