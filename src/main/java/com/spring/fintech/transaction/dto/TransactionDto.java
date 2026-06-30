package com.spring.fintech.transaction.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

	private int transactionId;
	private int senderWalletId;
	private int receiverWalletId;
	private double amount;
	private String type;
	private String status;
	private LocalDate createdAt;
}
