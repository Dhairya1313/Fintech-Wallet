package com.spring.fintech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.spring.fintech.service.UserService;
import com.spring.fintech.service.WalletService;

@RestController
public class Controller {
	
	private UserService userService;
	private WalletService walletService;
	
	@Autowired
	public Controller(UserService userService, WalletService walletService) {
		super();
		this.userService = userService;
		this.walletService = walletService;
	}
	
	
	
	
}
