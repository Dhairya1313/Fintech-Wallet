package com.spring.fintech.service;

import com.spring.fintech.entity.User;
import com.spring.fintech.entity.dto.UserDto;

public interface UserService {
	
	public UserDto registerUser(UserDto userDto);
	public UserDto authenticateUserByUserName(String userName, String password);
	public double checkWalletBalance(String userName);
}
