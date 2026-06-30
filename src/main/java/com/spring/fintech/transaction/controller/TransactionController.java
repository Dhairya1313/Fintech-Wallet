package com.spring.fintech.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.fintech.transaction.dto.TransactionDto;
import com.spring.fintech.transaction.service.TransactionService;
import com.spring.fintech.user.service.UserService;
import com.spring.fintech.wallet.service.WalletService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
private TransactionService transactionService;
	
	@Autowired
	public TransactionController(UserService userService, WalletService walletService, TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}
	
	
	@GetMapping
	public Page<TransactionDto> getTransactions(@RequestParam Integer walletId,@RequestParam Integer page){
		return transactionService.getWalletTransactions(walletId, page, 10);
	}
}
