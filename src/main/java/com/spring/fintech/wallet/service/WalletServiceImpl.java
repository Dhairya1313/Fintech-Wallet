package com.spring.fintech.wallet.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fintech.common.exception.InsufficientBalanceException;
import com.spring.fintech.common.exception.InvalidTransactionException;
import com.spring.fintech.common.exception.WalletNotFoundException;
import com.spring.fintech.transaction.dto.TransactionRequestDto;
import com.spring.fintech.transaction.dto.TransactionResponseDto;
import com.spring.fintech.transaction.service.TransactionService;
import com.spring.fintech.wallet.dto.WalletRequestDto;
import com.spring.fintech.wallet.dto.WalletResponseDto;
import com.spring.fintech.wallet.entity.Wallet;
import com.spring.fintech.wallet.repository.WalletRepository;

@Service("walletService")
public class WalletServiceImpl implements WalletService{
	
	private TransactionService transactionService;
	private WalletRepository walletRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public WalletServiceImpl(WalletRepository walletRepository,TransactionService transactionService, ModelMapper modelMapper) {
		super();
		this.walletRepository = walletRepository;
		this.modelMapper = modelMapper;
		this.transactionService = transactionService;
	}

	@Override
	public double checkBalance(int walletId) {
		
		Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> 
				new WalletNotFoundException(walletId));
		return wallet.getWalletBalance();
	}

	@Override
	public TransactionResponseDto addMoney(int walletId, double amount) {
		
			TransactionRequestDto transactionReqDto = new TransactionRequestDto();
			
			transactionReqDto.setAmount(amount);
			transactionReqDto.setReceiverWalletId(walletId);
			transactionReqDto.setSenderWalletId(walletId);
			TransactionResponseDto transactionResponseDto =  transactionService.addTransaction(transactionReqDto, walletId, walletId);
			
			Wallet wallet = walletRepository.findById(walletId).orElseThrow(()
					->new WalletNotFoundException(walletId));
			wallet.setWalletBalance(wallet.getWalletBalance() + amount);
			
			transactionResponseDto.setStatus("Success");
			transactionResponseDto.setCreatedAt(LocalDateTime.now());
			return transactionResponseDto;
		
	}

	@Override
	public TransactionResponseDto transferMoney(int walletId, double amount, int receiverWalletId) {
		
		Wallet senderWallet = walletRepository.findById(walletId).orElseThrow(() 
				-> new InvalidTransactionException("No active wallet found."));
		
		double balance = senderWallet.getWalletBalance();
		if(balance>=amount) {
			
			TransactionRequestDto transactionReqDto = new TransactionRequestDto();
			
			transactionReqDto.setAmount(amount);
			transactionReqDto.setReceiverWalletId(receiverWalletId);
			transactionReqDto.setSenderWalletId(walletId);
			TransactionResponseDto transactionResponseDto = transactionService.addTransaction(transactionReqDto, walletId, receiverWalletId);
			
			Wallet receiverWallet = walletRepository.findById(receiverWalletId)
					.orElseThrow(()->
			new InvalidTransactionException("Receiver wallet not found"));
			
			senderWallet.setWalletBalance(senderWallet.getWalletBalance()-amount);
			
			receiverWallet.setWalletBalance(receiverWallet.getWalletBalance() + amount);
			
			transactionResponseDto.setStatus("Success");
			return transactionResponseDto;
			
		}
		else
			throw new InsufficientBalanceException(balance, amount);
	}

	@Override
	public WalletResponseDto addWallet(WalletRequestDto walletRequestDto) {
		
		WalletResponseDto walletResponseDto = new WalletResponseDto();
		
		walletResponseDto.setCreatedAt(LocalDate.now());
		walletResponseDto.setWalletBalance(0.0);
		walletResponseDto.setStatus("Active");
		walletRepository.save(modelMapper.map(walletResponseDto, Wallet.class));
		
		return walletResponseDto;
	}
	
}
