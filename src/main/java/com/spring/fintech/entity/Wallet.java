package com.spring.fintech.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int walletId;
	@Column()
	private int userId;
	@Column(nullable = false)
	private double walletBalance;
	private String status;
	private LocalDate createdAt;
}
