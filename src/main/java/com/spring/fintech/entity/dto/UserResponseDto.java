package com.spring.fintech.entity.dto;

import java.time.LocalDate;

import com.spring.fintech.entity.Wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
	private int userId;
	private String userName;
	private String status;
	private LocalDate creadtedAt;
	private Wallet wallet;
}
