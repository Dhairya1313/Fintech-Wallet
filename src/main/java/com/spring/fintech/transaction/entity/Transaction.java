package com.spring.fintech.transaction.entity;

import java.time.LocalDate;

import com.spring.fintech.wallet.entity.Wallet;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_wallet_id", nullable = false)
	private Wallet senderWallet;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_wallet_id", nullable = false)
	private Wallet receiverWallet;
	
	
	private double amount;
	private String type;
	private String status;
	private LocalDate createdAt;
}