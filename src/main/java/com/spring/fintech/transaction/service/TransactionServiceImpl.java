package com.spring.fintech.transaction.service;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.fintech.common.exception.WalletNotFoundException;
import com.spring.fintech.transaction.dto.TransactionDto;
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
	public TransactionDto addTransaction(TransactionDto transactionDto, Integer senderWalletId,
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
		
		TransactionDto response =
		        modelMapper.map(saved, TransactionDto.class);

		response.setSenderWalletId(
		        saved.getSenderWallet().getWalletId());

		response.setReceiverWalletId(
		        saved.getReceiverWallet().getWalletId());

		return response;
	}

	@Override
	public TransactionDto getTransactionById(Integer transactionId) {
		
		Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(()-> new RuntimeException("Transaction not found"));
	
		return modelMapper.map(transaction, TransactionDto.class);
	}

	@Override
	public Page<TransactionDto> getWalletTransactions(Integer walletId, Integer page, Integer size) {
		
		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
		return transactionRepository.
				findBySenderWallet_WalletIdOrReceiverWallet_WalletId(walletId, walletId, pageable).map(transaction->
		modelMapper.map(transaction, TransactionDto.class));
	}

}
