package com.spring.fintech.user.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fintech.common.exception.DuplicateEmailException;
import com.spring.fintech.common.exception.DuplicateUsernameException;
import com.spring.fintech.common.exception.UnauthorizedOperationException;
import com.spring.fintech.common.exception.UserNotFoundException;
import com.spring.fintech.user.dto.UserRequestDto;
import com.spring.fintech.user.dto.UserResponseDto;
import com.spring.fintech.user.entity.User;
import com.spring.fintech.user.entity.UserStatus;
import com.spring.fintech.user.repository.UserRepository;
import com.spring.fintech.wallet.dto.WalletRequestDto;
import com.spring.fintech.wallet.entity.Wallet;
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
		this.walletService = walletService;
	}

	@Override
	public UserResponseDto registerUser(UserRequestDto userDto) {
		if(userRepository.existsByUserName(userDto.getUserName())) {
			throw new DuplicateUsernameException(userDto.getUserName());
		}
		if (userRepository.existsByEmail(userDto.getEmail())) {
		    throw new DuplicateEmailException(userDto.getEmail());
		}
		User user = modelMapper.map(userDto, User.class);

		user.setCreatedAt(LocalDate.now());
		user.setStatus(UserStatus.ACTIVE);

		user = userRepository.save(user);
		
		WalletRequestDto walletReqDto = new WalletRequestDto();
		walletReqDto.setUserId(user.getUserId());
		
		user.setWallet(modelMapper.map(walletService.addWallet(walletReqDto), Wallet.class)); 
		userRepository.save(user);
		
		return modelMapper.map(user, UserResponseDto.class);
	}

	@Override
	public UserResponseDto authenticateUserByUserName(String userName, String password) {
		
		User savedUser = userRepository.findUserByUserName(userName)
				.orElseThrow(()->
		new UserNotFoundException(userName));
		
		if(!savedUser.getPassword().equals(password))
			throw new UnauthorizedOperationException("Incorrect Password");
		if(!savedUser.getStatus().equals(UserStatus.ACTIVE))
			throw new UnauthorizedOperationException("User not active.");
		
		return modelMapper.map(savedUser, UserResponseDto.class);
		
			
	}

	@Override
	public double checkWalletBalance(String userName) {
		return userRepository.findUserByUserName(userName).get().getWallet().getWalletBalance();
	}
	
}
