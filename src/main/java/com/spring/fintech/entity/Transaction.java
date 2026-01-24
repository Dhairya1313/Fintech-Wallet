package com.spring.fintech.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sender_wallet_id")
	private int senderWalletId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "receiver_wallet_id")
	private int receiverWalletId;
	
	private double amount;
	private String type;
	private String status;
	private LocalDate createdAt;
}