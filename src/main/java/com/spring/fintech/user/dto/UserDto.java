package com.spring.fintech.user.dto;

import java.time.LocalDate;

import com.spring.fintech.wallet.entity.Wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private int userId;
	private String userName;
	private String password;
	private String email;
	
	private String status;
	private LocalDate creadtedAt;
	
	private Wallet wallet;
}
