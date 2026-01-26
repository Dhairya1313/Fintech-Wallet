package com.spring.fintech.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fintech.entity.Wallet;
import com.spring.fintech.entity.dto.TransactionDto;
import com.spring.fintech.entity.dto.WalletDto;
import com.spring.fintech.repository.WalletRepository;

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
		
		Wallet wallet = walletRepository.findById(walletId).orElseThrow(() 
				->new RuntimeException("No wallet exists."));
		return wallet.getWalletBalance();
	}

	@Override
	public TransactionDto addMoney(int walletId, double amount) {
		
			TransactionDto transactionDto = new TransactionDto();
			
			transactionDto.setAmount(amount);
			transactionDto.setCreatedAt(LocalDate.now());
			transactionDto.setReceiverWalletId(walletId);
			transactionDto.setSenderWalletId(walletId);
			transactionDto.setStatus("Failed");
			transactionService.addTransaction(transactionDto, walletId, walletId);
			
			Wallet wallet = walletRepository.findById(walletId).orElseThrow(()
					->new RuntimeException("Invalid receiver details."));
			wallet.setWalletBalance(wallet.getWalletBalance() + amount);
			
			transactionDto.setStatus("Success");
			transactionService.addTransaction(transactionDto, walletId, walletId);
			return transactionDto;
		
	}

	@Override
	public TransactionDto transferMoney(int walletId, double amount, int receiverWalletId) {
		
		Wallet senderWallet = walletRepository.findById(walletId).orElseThrow(() 
				-> new RuntimeException("No active wallet found."));
		
		double balance = senderWallet.getWalletBalance();
		if(balance>=amount) {
			
			TransactionDto transactionDto = new TransactionDto();
			
			transactionDto.setAmount(amount);
			transactionDto.setCreatedAt(LocalDate.now());
			transactionDto.setReceiverWalletId(receiverWalletId);
			transactionDto.setSenderWalletId(walletId);
			transactionDto.setStatus("Failed");
			transactionService.addTransaction(transactionDto, walletId, receiverWalletId);
			
			Wallet receiverWallet = walletRepository.findById(receiverWalletId).orElseThrow(()->new RuntimeException("No  found"));
			
			senderWallet.setWalletBalance(senderWallet.getWalletBalance()-amount);
			
			receiverWallet.setWalletBalance(receiverWallet.getWalletBalance() + amount);
			
			transactionDto.setStatus("Success");
			transactionService.addTransaction(transactionDto, walletId, receiverWalletId);
			return transactionDto;
			
		}
		else
			throw new RuntimeException("Insufficient Balance");
	}

	@Override
	public WalletDto addWallet(WalletDto walletDto) {
		walletDto.setCreatedAt(LocalDate.now());
		walletDto.setWalletBalance(0);
		walletDto.setStatus("Active");
		walletRepository.save(modelMapper.map(walletDto, Wallet.class));
		return walletDto;
	}
	
}
