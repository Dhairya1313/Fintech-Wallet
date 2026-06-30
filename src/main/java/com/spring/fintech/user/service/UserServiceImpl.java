package com.spring.fintech.user.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fintech.user.dto.UserDto;
import com.spring.fintech.user.dto.UserRequestDto;
import com.spring.fintech.user.dto.UserResponseDto;
import com.spring.fintech.user.entity.User;
import com.spring.fintech.user.repository.UserRepository;
import com.spring.fintech.wallet.dto.WalletDto;
import com.spring.fintech.wallet.entity.Wallet;
import com.spring.fintech.wallet.repository.WalletRepository;
import com.spring.fintech.wallet.service.WalletService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	private WalletService walletService;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserResponseDto registerUser(UserRequestDto userdto) {
		
		User savedUser = userRepository.save(modelMapper.map(userdto, User.class));
		
		savedUser.setCreadtedAt(LocalDate.now());
		savedUser.setStatus("Active");
		
		WalletDto walletDto = new WalletDto();
		walletDto.setUserId(savedUser.getUserId());
		
		savedUser.setWallet(modelMapper.map(walletService.addWallet(walletDto), Wallet.class)); 
		userRepository.save(savedUser);
		
		return modelMapper.map(savedUser, UserResponseDto.class);
	}

	@Override
	public UserResponseDto authenticateUserByUserName(String userName, String password) {
		
		User savedUser = userRepository.findUserByUserName(userName).orElseThrow(()->new RuntimeException("Invalid Username"));
		
		if(!savedUser.getPassword().equals(password))
			throw new RuntimeException("Incorrect Password!");
		if(!savedUser.getStatus().equalsIgnoreCase("Active"))
			throw new RuntimeException("User not active");
		
		return modelMapper.map(savedUser, UserResponseDto.class);
		
			
	}

	@Override
	public double checkWalletBalance(String userName) {
		return userRepository.findUserByUserName(userName).get().getWallet().getWalletBalance();
	}
	
}
