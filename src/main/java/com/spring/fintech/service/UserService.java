package com.spring.fintech.service;


import com.spring.fintech.entity.dto.UserRequestDto;
import com.spring.fintech.entity.dto.UserResponseDto;

public interface UserService {
	
	public UserResponseDto registerUser(UserRequestDto userDto);
	public UserResponseDto authenticateUserByUserName(String userName, String password);
	public double checkWalletBalance(String userName);
}
