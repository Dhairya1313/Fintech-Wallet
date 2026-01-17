package com.spring.fintech.service;

import com.spring.fintech.entity.User;

public interface UserService {
	
	public User registerUser(User user);
	public User authenticateUserByUserName(String userName, String password);
	
}
