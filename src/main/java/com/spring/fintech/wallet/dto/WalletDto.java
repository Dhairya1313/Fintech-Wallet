package com.spring.fintech.wallet.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {
	private int walletId;
	private int userId;
	private double walletBalance;
	private String status;
	private LocalDate createdAt;
}
