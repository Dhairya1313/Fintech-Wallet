package com.spring.fintech.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.fintech.transaction.dto.TransactionDto;
import com.spring.fintech.transaction.service.TransactionService;
import com.spring.fintech.user.service.UserService;
import com.spring.fintech.wallet.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {
	
	private UserService userService;
	private WalletService walletService;
	
	@Autowired
	public WalletController(UserService userService, WalletService walletService, TransactionService transactionService) {
		super();
		this.userService = userService;
		this.walletService = walletService;
	}
	
	@GetMapping("/balance")
	public double checkBalance(@RequestParam String userName) {
		return userService.checkWalletBalance(userName);
	}
	
	@PostMapping("/add-money")
	public TransactionDto addBalance(@RequestParam Integer walletId, @RequestParam Double amount) {
		return walletService.addMoney(walletId, amount);
	}
	
	@PostMapping("/transfer")
	public TransactionDto sendMoney(@RequestParam Integer walletId, @RequestParam Integer receiverWalletId, @RequestParam Double amount) {
		return walletService.transferMoney(walletId, amount, receiverWalletId);
	}
}
