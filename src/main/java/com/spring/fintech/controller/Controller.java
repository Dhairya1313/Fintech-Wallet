package com.spring.fintech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.fintech.entity.dto.TransactionDto;
import com.spring.fintech.entity.dto.UserDto;
import com.spring.fintech.service.TransactionService;
import com.spring.fintech.service.UserService;
import com.spring.fintech.service.WalletService;

@RestController
@RequestMapping("/api")
public class Controller {
	
	private UserService userService;
	private WalletService walletService;
	private TransactionService transactionService;
	
	@Autowired
	public Controller(UserService userService, WalletService walletService, TransactionService transactionService) {
		super();
		this.userService = userService;
		this.walletService = walletService;
		this.transactionService = transactionService;
	}
	
	@PostMapping("/auth/register")
	public UserDto register(@RequestBody UserDto userDto) {
		return userService.registerUser(userDto);
	}
	
	@PostMapping("/auth/login")
	public UserDto login(@RequestBody UserDto userDto) {
		return userService.authenticateUserByUserName(userDto.getUserName(), userDto.getPassword());
	}
	
	@GetMapping("/wallet/balance")
	public double checkBalance(@RequestBody UserDto userDto) {
		return userService.checkWalletBalance(userDto.getUserName());
	}
	
	@PostMapping("/wallet/add-money")
	public TransactionDto addBalance(@RequestParam Integer walletId, Double amount) {
		return walletService.addMoney(walletId, amount);
	}
	
	@PostMapping("/wallet/transfer")
	public TransactionDto sendMoney(@RequestParam Integer walletId, Integer receiverWalletId, Double amount) {
		return walletService.transferMoney(walletId, amount, receiverWalletId);
	}
	
	@GetMapping("/transactions")
	public Page<TransactionDto> getTransactions(@RequestParam Integer walletId, Integer page){
		return transactionService.getWalletTransactions(walletId, page, 10);
	}
}
