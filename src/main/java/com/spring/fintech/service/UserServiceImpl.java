package com.spring.fintech.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fintech.entity.User;
import com.spring.fintech.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User authenticateUserByUserName(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
