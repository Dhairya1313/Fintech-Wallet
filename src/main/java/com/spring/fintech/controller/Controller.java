package com.spring.fintech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
}
