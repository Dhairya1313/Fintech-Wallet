package com.spring.fintech.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fintech.repository.WalletRepository;

@Service("walletService")
public class WalletServiceImpl implements WalletService{
	
	private WalletRepository walletRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public WalletServiceImpl(WalletRepository walletRepository, ModelMapper modelMapper) {
		super();
		this.walletRepository = walletRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public double checkBalance(int walletId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double addMoney(int walletId, double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double transferMoney(int walletId, double amount, int receiverWalletId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
