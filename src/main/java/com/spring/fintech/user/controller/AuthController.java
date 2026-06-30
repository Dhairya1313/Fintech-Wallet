package com.spring.fintech.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.fintech.transaction.service.TransactionService;
import com.spring.fintech.user.dto.UserRequestDto;
import com.spring.fintech.user.dto.UserResponseDto;
import com.spring.fintech.user.service.UserService;
import com.spring.fintech.wallet.service.WalletService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private UserService userService;
	
	@Autowired
	public AuthController(UserService userService, WalletService walletService, TransactionService transactionService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public UserResponseDto register(@RequestBody UserRequestDto userRequestDto) {
		return userService.registerUser(userRequestDto);
	}
	
	@PostMapping("/login")
	public UserResponseDto login(@RequestParam String userName, @RequestParam String password) {
		return userService.authenticateUserByUserName(userName, password);
	}
}
