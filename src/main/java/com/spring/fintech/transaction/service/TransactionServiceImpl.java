package com.spring.fintech.transaction.service;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.fintech.common.exception.WalletNotFoundException;
import com.spring.fintech.transaction.dto.TransactionRequestDto;
import com.spring.fintech.transaction.dto.TransactionResponseDto;
import com.spring.fintech.transaction.entity.Transaction;
import com.spring.fintech.transaction.repository.TransactionRepository;
import com.spring.fintech.wallet.entity.Wallet;
import com.spring.fintech.wallet.repository.WalletRepository;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService{

	private TransactionRepository transactionRepository;
	private ModelMapper modelMapper;
	private WalletRepository walletRepository;
	
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository, ModelMapper modelMapper, WalletRepository walletRepository) {
		super();
		this.transactionRepository = transactionRepository;
		this.modelMapper = modelMapper;
		this.walletRepository = walletRepository;
	}

	@Override
	public TransactionResponseDto addTransaction(TransactionRequestDto transactionDto, Integer senderWalletId,
			Integer receiverWalletId) {
		
		Transaction transaction = modelMapper
				.map(transactionDto, Transaction.class);
		
		
		Wallet senderWallet = walletRepository.findById(senderWalletId)
				.orElseThrow(()->
				new WalletNotFoundException(senderWalletId));
		Wallet receiverWallet =
		        walletRepository.findById(receiverWalletId)
		        .orElseThrow(() ->
		        new WalletNotFoundException(receiverWalletId));
		
		transaction.setSenderWallet(senderWallet);
		transaction.setReceiverWallet(receiverWallet);
		Transaction saved = transactionRepository.save(transaction);
		
		TransactionResponseDto response =
		        modelMapper.map(saved, TransactionResponseDto.class);

		response.setSenderWalletId(
		        saved.getSenderWallet().getWalletId());

		response.setReceiverWalletId(
		        saved.getReceiverWallet().getWalletId());

		return response;
	}

	@Override
	public TransactionResponseDto getTransactionById(Integer transactionId) {
		
		Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(()-> new RuntimeException("Transaction not found"));
	
		return modelMapper.map(transaction, TransactionResponseDto.class);
	}

	@Override
	public Page<TransactionResponseDto> getWalletTransactions(Integer walletId, Integer page, Integer size) {
		
		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
		return transactionRepository.
				findBySenderWallet_WalletIdOrReceiverWallet_WalletId(walletId, walletId, pageable).map(transaction->
		modelMapper.map(transaction, TransactionResponseDto.class));
	}

}
