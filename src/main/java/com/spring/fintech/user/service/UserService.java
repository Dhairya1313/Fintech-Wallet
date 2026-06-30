package com.spring.fintech.user.service;


import com.spring.fintech.user.dto.UserRequestDto;
import com.spring.fintech.user.dto.UserResponseDto;

public interface UserService {
	
	public UserResponseDto registerUser(UserRequestDto userDto);
	public UserResponseDto authenticateUserByUserName(String userName, String password);
	public double checkWalletBalance(String userName);
}
