package com.spring.fintech.transaction.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {

	@NotNull(message = "Sender wallet is required.")
	private Integer senderWalletId;
	@NotNull(message = "Receiver wallet is required.")
	private Integer receiverWalletId;
	@Positive(message = "Amount must be greater than zero.")
	private Double amount;
}
